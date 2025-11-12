package com.itheima.aop;

import com.itheima.mapper.OperateLogMapper;
import com.itheima.pojo.OperateLog;
import com.itheima.utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
@Slf4j
public class OperateLogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.itheima.anno.Log)")
    public Object recordOperateLog(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取当前操作人ID（这里假设从SecurityContext或ThreadLocal中获取）
        Integer operateEmpId = getCurrentUserId();

        // 记录开始时间
        long startTime = System.currentTimeMillis();

        // 获取目标类名和方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();

        // 获取方法参数
        Object[] args = joinPoint.getArgs();
        String methodParams = this.parseParams(args);

        Object result = null;
        try {
            // 执行目标方法
            result = joinPoint.proceed();
            return result;
        } finally {
            // 记录结束时间
            long endTime = System.currentTimeMillis();
            long costTime = endTime - startTime;

            // 构造操作日志对象
            OperateLog operateLog = new OperateLog();
            operateLog.setOperateEmpId(operateEmpId);
            operateLog.setOperateTime(LocalDateTime.now());
            operateLog.setClassName(className);
            operateLog.setMethodName(methodName);
            operateLog.setMethodParams(methodParams);
            operateLog.setReturnValue(result != null ? result.toString() : "");
            operateLog.setCostTime(costTime);

            // 保存操作日志
            try {
                operateLogMapper.insert(operateLog);
            } catch (Exception e) {
                log.error("保存操作日志失败: {}", e.getMessage());
            }
        }
    }

    /**
     * 解析方法参数
     *
     * @param params 参数数组
     * @return 参数字符串
     */
    private String parseParams(Object[] params) {
        if (params == null || params.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Object param : params) {
            sb.append(param).append(";");
        }
        return sb.toString();
    }

    /**
     * 获取当前操作用户ID
     * 这里需要根据实际项目中的用户认证方式来实现
     *
     * @return 用户ID
     */
    private Integer getCurrentUserId() {
        // 示例：从Spring Security中获取当前用户ID
        // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
        //     return ((UserDetails) authentication.getPrincipal()).getId();
        // }
        
        // 示例：从ThreadLocal中获取
         return CurrentHolder.getCurrentId();

        // 临时返回默认值
        // return 1;
    }
}
