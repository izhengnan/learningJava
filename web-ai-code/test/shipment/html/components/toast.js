// 通用气泡提示组件

/**
 * 显示登录过期气泡提示
 * 该函数会显示一个气泡提示，提示用户登录已过期
 * 3秒后自动跳转到登录页面
 */
function showLoginExpiredToast() {
    // 创建气泡提示元素
    const toastContainer = document.createElement('div');
    toastContainer.className = 'login-expired-toast';
    toastContainer.innerHTML = `
        <div class="toast-content">
            <i class="bi bi-exclamation-circle"></i>
            <span>登录已过期，请重新登录</span>
        </div>
        <div class="toast-progress"></div>
    `;
    
    // 添加到body
    document.body.appendChild(toastContainer);
    
    // 触发显示动画
    setTimeout(() => {
        toastContainer.classList.add('show');
    }, 10);
    
    // 3秒后跳转到登录页面
    setTimeout(() => {
        // 使用相对于网站根目录的路径，确保在任何目录层级都能正确跳转
        window.location.href = '/html/login.html';
    }, 3000);
}

// 添加CSS样式
function addToastStyles() {
    // 检查是否已经添加了样式
    if (document.getElementById('login-toast-styles')) {
        return;
    }
    
    const style = document.createElement('style');
    style.id = 'login-toast-styles';
    style.textContent = `
        .login-expired-toast {
            position: fixed;
            top: 20px;
            left: 50%;
            transform: translateX(-50%);
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
            padding: 16px 20px;
            display: flex;
            align-items: center;
            min-width: 300px;
            max-width: 400px;
            z-index: 9999;
            transition: opacity 0.4s ease-in-out, transform 0.4s ease-in-out;
            border-left: 4px solid #dc3545;
            opacity: 0;
        }
        
        .login-expired-toast.show {
            opacity: 1;
            transform: translateX(-50%);
        }
        
        .toast-content {
            display: flex;
            align-items: center;
            gap: 12px;
        }
        
        .toast-content i {
            color: #dc3545;
            font-size: 20px;
        }
        
        .toast-content span {
            color: #333;
            font-size: 14px;
            font-weight: 500;
        }
        
        .toast-progress {
            position: absolute;
            bottom: 0;
            left: 0;
            height: 3px;
            background-color: #dc3545;
            width: 100%;
            animation: progress 3s linear forwards;
        }
        
        @keyframes progress {
            from { width: 100%; }
            to { width: 0%; }
        }
        
        /* 响应式调整 */
        @media (max-width: 576px) {
            .login-expired-toast {
                min-width: auto;
                width: calc(100% - 40px);
                left: 50%;
                transform: translateX(-50%);
            }
            
            .login-expired-toast.show {
                transform: translateX(-50%);
            }
        }
    `;
    
    document.head.appendChild(style);
}

// 初始化气泡提示组件
document.addEventListener('DOMContentLoaded', function() {
    addToastStyles();
});