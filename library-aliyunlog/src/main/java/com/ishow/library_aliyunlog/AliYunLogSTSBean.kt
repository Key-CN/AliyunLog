package com.ishow.library_aliyunlog

data class AliYunLogSTSBean(
    var accessKeyId: String,
    var accessKeySecret: String,
    var securityToken: String,
    var expiration: String,
)