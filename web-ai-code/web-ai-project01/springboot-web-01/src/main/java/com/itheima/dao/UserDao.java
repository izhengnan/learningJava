package com.itheima.dao;

import cn.hutool.core.io.IoUtil;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public interface UserDao {
    public List<String> findAll();
}
