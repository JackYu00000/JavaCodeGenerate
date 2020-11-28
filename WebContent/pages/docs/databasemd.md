#### 表: user_leave  user_leave
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|id|id|VARCHAR|**必填**|null||
|user_id|user_id|VARCHAR|**必填**|null||
|user_name|user_name|VARCHAR||null||
|begin_time|begin_time|DATETIME||null||
|end_time|end_time|DATETIME||null||
|reason|reason|VARCHAR||null||
|days|days|INT||null||
|process_instance_Id|process_instance_Id|VARCHAR||null||
|status|status|VARCHAR||null||
|create_date|create_date|DATETIME||null||
|create_by|create_by|VARCHAR||null||
|update_date|update_date|DATETIME||null||
|update_by|update_by|VARCHAR||null||
|task_name|task_name|VARCHAR||null||
|urlpath|urlpath|VARCHAR||null||
|submittimes|submittimes|INT||null||

##### 查询sql
> select  id,user_id,user_name,begin_time,end_time,reason,days,process_instance_Id,status,create_date,create_by,update_date,update_by,task_name,urlpath,submittimes from user_leave




#### 表: tl_book_theme_re  tl_book_theme_re
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|书单id|book_id|VARCHAR||null|书单id|
|主题id|theme_id|VARCHAR||null|主题id|
|create_by|create_by|VARCHAR||null|create_by|
|create_date|create_date|DATETIME||null|create_date|

##### 查询sql
> select  id,book_id,theme_id,create_by,create_date from tl_book_theme_re




#### 表: t_user_prefer_theme_ref  t_user_prefer_theme_ref
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|偏好id|prefer_id|VARCHAR||null|偏好id|
|主题id|theme_id|VARCHAR||null|主题id|

##### 查询sql
> select  id,prefer_id,theme_id from t_user_prefer_theme_ref




#### 表: t_user_perfer_genre_ref  t_user_perfer_genre_ref
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|偏好id|prefer_id|VARCHAR||null|偏好id|
|体裁id|genre_id|VARCHAR||null|体裁id|

##### 查询sql
> select  id,prefer_id,genre_id from t_user_perfer_genre_ref




#### 表: t_user_ordered_goods  t_user_ordered_goods
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|id|id|VARCHAR|**必填**|null||
|主键|sortcode|VARCHAR||null|主键|
|app 用户id|user_id|VARCHAR||null|app 用户id|
|名称|name|VARCHAR|**必填**|null|名称|
|原价，单位分|original_price|INT||null|原价，单位分|
|价格，单位分|price|INT||null|价格，单位分|
|提交订单时间|order_create_date|DATETIME||null|提交订单时间|
|good_extend|good_extend|TEXT||null|商品包含具体物品，对应值，json|
|good_capture|good_capture|TEXT||null|商品包含具体物品，介绍信息，json|
|支付方式|pay_type|VARCHAR||null|支付方式|
|支付时间|pay_time|DATETIME||null|支付时间|
|pay_statu|pay_statu|INT||null|枚举赋值：*UserOrderedGoodsPayStatu*
0&nbsp;:&nbsp;待支付,
1&nbsp;:&nbsp;已支付|
|支付状态更新时间|pay_statu_updatetime|DATETIME||null|支付状态更新时间|
|create_by|create_by|VARCHAR||null|create_by|
|create_date|create_date|DATETIME||null|create_date|
|订单id|order_id|VARCHAR||null|订单id|
|report_id|report_id|VARCHAR||null|报告id，（学习包购买时添加最新报告id）|

##### 查询sql
> select  id,sortcode,user_id,name,original_price,price,order_create_date,good_extend,good_capture,pay_type,pay_time,pay_statu,pay_statu_updatetime,create_by,create_date,order_id,report_id from t_user_ordered_goods




#### 表: t_user_gl_dictionary  t_user_gl_dictionary
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|id|id|INT|**必填**|null||
|年级|grade|INT||null|年级|
|级别|level|INT||null|级别|
|GL值|gl|VARCHAR||null|GL值|

##### 查询sql
> select  id,grade,level,gl from t_user_gl_dictionary




#### 表: t_sys_user_read_prefer_quiz_question  t_sys_user_read_prefer_quiz_question
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|题目原文，长内容|question_original|VARCHAR||null|题目原文，长内容|
|题目|question|VARCHAR|**必填**|null|题目|
|question_type|question_type|INT||null|枚举赋值：*SysUserReadPreferQuizQuestionQuestionType*
1&nbsp;:&nbsp;文字,
2&nbsp;:&nbsp;图片,
3&nbsp;:&nbsp;图文|
|img 题图|question_image|VARCHAR||null|img 题图|
|顺序|indexs|INT|**必填**|null|顺序|
|create_by|create_by|VARCHAR||null|create_by|
|create_date|create_date|DATETIME||null|create_date|
|update_by|update_by|VARCHAR||null|update_by|
|update_date|update_date|DATETIME||null|update_date|
|status|status|INT|**必填**|null|枚举赋值：*SysUserReadPreferQuizQuestionStatus*
0&nbsp;:&nbsp;停用,
1&nbsp;:&nbsp;启用|
|编码，唯一|idcode|VARCHAR||null|编码，唯一|

##### 查询sql
> select  id,question_original,question,question_type,question_image,indexs,create_by,create_date,update_by,update_date,status,idcode from t_sys_user_read_prefer_quiz_question




#### 表: t_sys_user_read_prefer_quiz_answer  t_sys_user_read_prefer_quiz_answer
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|题目id|question_id|VARCHAR||null|题目id|
|答案内容|answer_content|VARCHAR|**必填**|null|答案内容|
|is_right|is_right|INT||null|枚举赋值：*SysUserReadPreferQuizAnswerIsRight*
0&nbsp;:&nbsp;不是,
1&nbsp;:&nbsp;是|
|create_by|create_by|VARCHAR||null|create_by|
|create_date|create_date|DATETIME||null|create_date|
|update_by|update_by|VARCHAR||null|update_by|
|update_date|update_date|DATETIME||null|update_date|
|编码，唯一|idcode|VARCHAR||null|编码，唯一|

##### 查询sql
> select  id,question_id,answer_content,is_right,create_by,create_date,update_by,update_date,idcode from t_sys_user_read_prefer_quiz_answer




#### 表: t_sys_point_task  t_sys_point_task
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|任务名称|task_name|VARCHAR||null|任务名称|
|task_code|task_code|VARCHAR||null|任务code，用于计算积分使用|
|task_limit|task_limit|TEXT||null|扩展规则，获取限制，每日次数等|
|任务描述|task_intro|VARCHAR||null|任务描述|
|积分|points|INT||null|积分|
|status|status|INT||null|枚举赋值：*SysPointTaskStatus*
0&nbsp;:&nbsp;无效,
1&nbsp;:&nbsp;有效|
|有效期起|available|DATETIME||null|有效期起|
|有效期止|expiry|DATETIME||null|有效期止|
|create_by|create_by|VARCHAR||null|create_by|
|create_date|create_date|DATETIME||null|create_date|
|update_by|update_by|VARCHAR||null|update_by|
|update_date|update_date|DATETIME||null|update_date|

##### 查询sql
> select  id,task_name,task_code,task_limit,task_intro,points,status,available,expiry,create_by,create_date,update_by,update_date from t_sys_point_task




#### 表: t_sys_organize_info  t_sys_organize_info
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|名称|name|VARCHAR|**必填**|null|名称|
|联系人|liaison|VARCHAR||null|联系人|
|手机号sha|mobile_sha|VARCHAR||null|手机号sha|
|手机号aes|mobile_aes|VARCHAR||null|手机号aes|
|邮箱sha|email_sha|VARCHAR||null|邮箱sha|
|邮箱aes|email_aes|VARCHAR||null|邮箱aes|
|verify_step|verify_step|INT||null|枚举赋值：*SysOrganizeInfoVerifyStep*
1&nbsp;:&nbsp;未提交,
2&nbsp;:&nbsp;待认证,
3&nbsp;:&nbsp;已反馈|
|verify_status|verify_status|INT||null|枚举赋值：*SysOrganizeInfoVerifyStatus*
1&nbsp;:&nbsp;待认证,
2&nbsp;:&nbsp;通过,
3&nbsp;:&nbsp;未通过|
|认证通过时间|verify_pass_date|DATETIME||null|认证通过时间|
|地址省|province|VARCHAR||null|地址省|
|地址市|city|VARCHAR||null|地址市|
|地址区|district|VARCHAR||null|地址区|
|地址|address|VARCHAR||null|地址|
|营业执照号|Column_16|VARCHAR||null|营业执照号|
|create_by|create_by|VARCHAR||null|create_by|
|create_date|create_date|DATETIME||null|create_date|
|update_by|update_by|VARCHAR||null|update_by|
|update_date|update_date|DATETIME||null|update_date|

##### 查询sql
> select  id,name,liaison,mobile_sha,mobile_aes,email_sha,email_aes,verify_step,verify_status,verify_pass_date,province,city,district,address,Column_16,create_by,create_date,update_by,update_date from t_sys_organize_info




#### 表: t_sys_notice  t_sys_notice
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|id|id|VARCHAR||null||
|标题|title|VARCHAR||null|标题|
|内容|content|VARCHAR||null|内容|
|expand_val|expand_val|TEXT||null|类型，打开方式，值等，参照 appbanner|
|目标用户|clients|VARCHAR||null|目标用户|
|发送人|send_user_id|VARCHAR||null|发送人|
|发送时间|send_time|DATETIME||null|发送时间|
|create_by|create_by|VARCHAR||null|create_by|
|create_date|create_date|DATETIME||null|create_date|

##### 查询sql
> select  id,title,content,expand_val,clients,send_user_id,send_time,create_by,create_date from t_sys_notice




#### 表: t_sys_message  t_sys_message
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|id|id|VARCHAR||null||
|标题|title|VARCHAR||null|标题|
|内容|content|VARCHAR||null|内容|
|expand_val|expand_val|TEXT||null|类型，打开方式，值等，参照 appbanner|
|目标用户|clients|VARCHAR||null|目标用户|
|发送人|send_user_id|VARCHAR||null|发送人|
|发送时间|send_time|DATETIME||null|发送时间|
|create_by|create_by|VARCHAR||null|create_by|
|create_date|create_date|DATETIME||null|create_date|
|接收人|user_id|VARCHAR||null|接收人|

##### 查询sql
> select  id,title,content,expand_val,clients,send_user_id,send_time,create_by,create_date,user_id from t_sys_message




#### 表: t_sys_home  t_sys_home
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|type|type|INT|**必填**|null|枚举赋值：*SysHomeType*
1&nbsp;:&nbsp;阅读课,
2&nbsp;:&nbsp;词汇课,
3&nbsp;:&nbsp;名家讲堂,
4&nbsp;:&nbsp;书单,
5&nbsp;:&nbsp;家长学堂,
6&nbsp;:&nbsp;学习包,
7&nbsp;:&nbsp;测试,
8&nbsp;:&nbsp;URL,
9&nbsp;:&nbsp;会员卡|
|img 展示图片|show_img|VARCHAR|**必填**|null|img 展示图片|
|内容id|val|VARCHAR||null|内容id|
|顺序|indexs|INT|**必填**|null|顺序|
|生效时间起|available|DATETIME|**必填**|null|生效时间起|
|生效时间止|expiry|DATETIME|**必填**|null|生效时间止|
|create_by|create_by|VARCHAR||null|create_by|
|create_date|create_date|DATETIME||null|create_date|
|update_by|update_by|VARCHAR||null|update_by|
|update_date|update_date|DATETIME||null|update_date|

##### 查询sql
> select  id,type,show_img,val,indexs,available,expiry,create_by,create_date,update_by,update_date from t_sys_home




#### 表: t_sys_commodity_sale  t_sys_commodity_sale
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|id|id|VARCHAR|**必填**|null||
|商品类别code|sortcode|VARCHAR||null|商品类别code|
|策略id|policy_id|VARCHAR||null|策略id|
|添加时间|add_date|DATETIME||null|添加时间|
|失效时间|end_date|DATETIME||null|失效时间|
|status|status|INT||null|枚举赋值：*SysCommoditySaleStatus*
1&nbsp;:&nbsp;是,
2&nbsp;:&nbsp;不是|
|priority|priority|INT||null|优先级，数值越大越优先|

##### 查询sql
> select  id,sortcode,policy_id,add_date,end_date,status,priority from t_sys_commodity_sale




#### 表: t_sys_commodity  t_sys_commodity
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键，唯一，不重复|sortcode|VARCHAR|**必填**|null|主键，唯一，不重复|
|名称|name|VARCHAR|**必填**|null|名称|
|商品策略|ptype|VARCHAR||null|商品策略|
|简述|introduction|VARCHAR||null|简述|
|原价，单位分|original_price|INT||null|原价，单位分|
|售价，单位分|price|INT||null|售价，单位分|
|status|status|INT||null|枚举赋值：*SysCommodityStatus*
0&nbsp;:&nbsp;无效,
1&nbsp;:&nbsp;有效|
|生效状态更新时间|status_updatetime|DATETIME||null|生效状态更新时间|
|ingredients|ingredients|TEXT||null|商品包含物品，json|
|create_by|create_by|VARCHAR||null|create_by|
|create_date|create_date|DATETIME||null|create_date|

##### 查询sql
> select  sortcode,name,ptype,introduction,original_price,price,status,status_updatetime,ingredients,create_by,create_date from t_sys_commodity




#### 表: t_sys_banner  t_sys_banner
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|名称|name|VARCHAR|**必填**|null|名称|
|描述|description|VARCHAR|**必填**|null|描述|
|img 图片_App|image_app|VARCHAR||null|img 图片_App|
|img 图片_PC|image_pc|VARCHAR||null|img 图片_PC|
|type|type|INT||null|枚举赋值：*SysBannerType*
1&nbsp;:&nbsp;阅读课,
2&nbsp;:&nbsp;词汇课,
3&nbsp;:&nbsp;名家讲堂,
4&nbsp;:&nbsp;书单,
5&nbsp;:&nbsp;家长学堂,
6&nbsp;:&nbsp;学习包,
7&nbsp;:&nbsp;测试,
8&nbsp;:&nbsp;URL,
9&nbsp;:&nbsp;会员卡|
|content_type|content_type|VARCHAR|**必填**|null|枚举赋值：*SysBannerContentType*
LIST&nbsp;:&nbsp;列表页,
DETAIL&nbsp;:&nbsp;详情页,
URLING&nbsp;:&nbsp;app内打卡网页get,
URLINP&nbsp;:&nbsp;app内打卡网页post,
URLOUT&nbsp;:&nbsp;跳出app打开网页|
|val|val|VARCHAR||null|url 值,或者内容id|
|show_statu|show_statu|INT|**必填**|null|枚举赋值：*SysBannerShowStatu*
0&nbsp;:&nbsp;未展示,
1&nbsp;:&nbsp;展示中|
|展示时间起|show_end|DATETIME|**必填**|null|展示时间起|
|展示时间止|show_begin|DATETIME|**必填**|null|展示时间止|
|顺序|indexs|INT|**必填**|null|顺序|
|create_by|create_by|VARCHAR||null|create_by|
|create_date|create_date|DATETIME||null|create_date|
|update_by|update_by|VARCHAR||null|update_by|
|update_date|update_date|DATETIME||null|update_date|

##### 查询sql
> select  id,name,description,image_app,image_pc,type,content_type,val,show_statu,show_end,show_begin,indexs,create_by,create_date,update_by,update_date from t_sys_banner




#### 表: t_sys_articles  t_sys_articles
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|type|type|INT|**必填**|null|枚举赋值：*SysArticlesType*
1&nbsp;:&nbsp;产品介绍,
2&nbsp;:&nbsp;服务协议,
3&nbsp;:&nbsp;隐私条款,
4&nbsp;:&nbsp;蓝钻说明,
5&nbsp;:&nbsp;会员权益,
6&nbsp;:&nbsp;Star测试介绍,
7&nbsp;:&nbsp;PC前端首页内容|
|标题|title|VARCHAR|**必填**|null|标题|
|内容|content|VARCHAR||null|内容|
|create_by|create_by|VARCHAR||null|create_by|
|create_date|create_date|DATETIME||null|create_date|
|update_by|update_by|VARCHAR||null|update_by|
|update_date|update_date|DATETIME||null|update_date|

##### 查询sql
> select  id,type,title,content,create_by,create_date,update_by,update_date from t_sys_articles




#### 表: t_sys_app  t_sys_app
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|版本号|version_code|INT|**必填**|null|版本号|
|更新号|version_name|VARCHAR|**必填**|null|更新号|
|platform|platform|VARCHAR|**必填**|null|平台 1:安卓 2：苹果
|
|下载地址|down_url|TEXT||null|下载地址|
|发布时间|release_time|DATETIME||null|发布时间|
|发布介绍|release_note|VARCHAR||null|发布介绍|
|force_update|force_update|INT||null|枚举赋值：*SysAppForceUpdate*
0&nbsp;:&nbsp;不是,
1&nbsp;:&nbsp;是|
|on_selling|on_selling|INT||null|枚举赋值：*SysAppOnSelling*
0&nbsp;:&nbsp;未上,
1&nbsp;:&nbsp;以上|
|创建人|create_by|VARCHAR||null|创建人|
|创建时间|create_date|DATETIME||null|创建时间|
|更新人|update_by|VARCHAR||null|更新人|
|更新时间|update_date|DATETIME||null|更新时间|

##### 查询sql
> select  id,version_code,version_name,platform,down_url,release_time,release_note,force_update,on_selling,create_by,create_date,update_by,update_date from t_sys_app




#### 表: t_province  t_province
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|PROVINCE_SN|PROVINCE_SN|INT|**必填**|null||
|PROVINCE_NAME|PROVINCE_NAME|VARCHAR||null||

##### 查询sql
> select  PROVINCE_SN,PROVINCE_NAME from t_province




#### 表: t_organization_info  t_organization_info
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|id|id|VARCHAR|**必填**|null|主键，与机构用户表一致|
|名字|name|VARCHAR|**必填**|null|名字|
|img photo|photo|VARCHAR||null|img photo|
|联系人|contacts|VARCHAR||null|联系人|
|微信号|wechat_number|VARCHAR||null|微信号|
|微信号订阅号|wechat_rss|VARCHAR||null|微信号订阅号|
|微信号服务号|wechat_service|VARCHAR||null|微信号服务号|
|org_type|org_type|VARCHAR||null|枚举赋值：*OrganizationInfoOrgType*
1&nbsp;:&nbsp;绘本馆,
2&nbsp;:&nbsp;实体书店,
3&nbsp;:&nbsp;教育机构,
4&nbsp;:&nbsp;线上商城|
|公司地址|address|VARCHAR||null|公司地址|
|营业执照编号|business_license|VARCHAR||null|营业执照编号|
|创建人|create_by|VARCHAR||null|创建人|
|创建时间|create_date|DATETIME||null|创建时间|
|更新人|update_by|VARCHAR||null|更新人|
|enable_status|enable_status|VARCHAR||null|是否启用: 0 否1是|
|更新时间|update_date|DATETIME||null|更新时间|

##### 查询sql
> select  id,name,photo,contacts,wechat_number,wechat_rss,wechat_service,org_type,address,business_license,create_by,create_date,update_by,enable_status,update_date from t_organization_info




#### 表: t_organization  t_organization
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|username|username|VARCHAR||null|username|
|password|password|VARCHAR||null|password|
|邮箱aes加密值|email|TEXT||null|邮箱aes加密值|
|邮箱sha|email_sha|VARCHAR||null|邮箱sha|
|手机号aes加密值|mobile|TEXT||null|手机号aes加密值|
|手机号sha|mobile_sha|VARCHAR||null|手机号sha|
|创建人|create_by|VARCHAR||null|创建人|
|更新人|update_by|VARCHAR||null|更新人|
|创建时间|create_date|DATETIME||null|创建时间|
|更新时间|update_date|DATETIME||null|更新时间|
|enable_status|enable_status|INT||null|是否启用:0: 否1: 是|
|del_flag|del_flag|INT||null|枚举赋值：*OrganizationDelFlag*
0&nbsp;:&nbsp;可用,
1&nbsp;:&nbsp;封禁|

##### 查询sql
> select  id,username,password,email,email_sha,mobile,mobile_sha,create_by,update_by,create_date,update_date,enable_status,del_flag from t_organization




#### 表: t_goods_sale_policy  t_goods_sale_policy
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|id|ID|INT|**必填**|null|id|
|策略名称|NAME|VARCHAR||null|策略名称|
|短简介|INTRO|VARCHAR||null|短简介|
|special_price|special_price|INT||null|枚举赋值：*GoodsSalePolicySpecialPrice*
0&nbsp;:&nbsp;无效,
1&nbsp;:&nbsp;有效|
|价格，单位分|PRICE|INT||null|价格，单位分|
|原价，单位分|ORIGINAL|INT||null|原价，单位分|
|销售时间起|START_TIME|DATETIME||null|销售时间起|
|销售时间起|END_TIME|DATETIME||null|销售时间起|
|须知|CONTENT|TEXT||null|须知|
|备注|MEMO|VARCHAR||null|备注|
|policy_extend|policy_extend|TEXT||null|策略拓展，存储json，预留|
|create_by|create_by|VARCHAR||null|create_by|
|create_date|create_date|DATETIME||null|create_date|
|priority|priority|INT||null|优先级，数值越大越优先|

##### 查询sql
> select  ID,NAME,INTRO,special_price,PRICE,ORIGINAL,START_TIME,END_TIME,CONTENT,MEMO,policy_extend,create_by,create_date,priority from t_goods_sale_policy




#### 表: t_genre_theme_rel  t_genre_theme_rel
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|id|id|VARCHAR|**必填**|null|id|
|体裁id|genre_id|VARCHAR||null|体裁id|
|主题id|theme_id|VARCHAR||null|主题id|

##### 查询sql
> select  id,genre_id,theme_id from t_genre_theme_rel




#### 表: t_dict_user_prefer  t_dict_user_prefer
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|名称|name|VARCHAR|**必填**|null|名称|
|描述|description|VARCHAR||null|描述|
|img 图标|icon|VARCHAR||null|img 图标|
|btype|btype|VARCHAR||null|枚举赋值：*DictUserPreferBtype*
13B&nbsp;:&nbsp;1-3年级基础类,
49B&nbsp;:&nbsp;4-9年级基础类|
|status|status|INT||null|枚举赋值：*DictUserPreferStatus*
0&nbsp;:&nbsp;停用,
1&nbsp;:&nbsp;启用|
|create_by|create_by|VARCHAR||null|create_by|
|create_date|create_date|DATETIME||null|create_date|
|update_by|update_by|VARCHAR||null|update_by|
|update_date|update_date|DATETIME||null|update_date|

##### 查询sql
> select  id,name,description,icon,btype,status,create_by,create_date,update_by,update_date from t_dict_user_prefer




#### 表: t_dict_theme  t_dict_theme
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|名称|name|VARCHAR|**必填**|null|名称|
|描述|description|VARCHAR|**必填**|null|描述|
|status|status|INT||null|枚举赋值：*DictThemeStatus*
0&nbsp;:&nbsp;停用,
1&nbsp;:&nbsp;启用|
|create_by|create_by|VARCHAR||null|create_by|
|create_date|create_date|DATETIME||null|create_date|
|update_by|update_by|VARCHAR||null|update_by|
|update_date|update_date|DATETIME||null|update_date|
|编码，唯一|idcode|VARCHAR||null|编码，唯一|

##### 查询sql
> select  id,name,description,status,create_by,create_date,update_by,update_date,idcode from t_dict_theme




#### 表: t_dict_parent_course_class  t_dict_parent_course_class
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|id|id|VARCHAR|**必填**|null|id|
|名称|name|VARCHAR||null|名称|
|描述|description|VARCHAR||null|描述|
|status|status|INT||null|枚举赋值：*DictParentCourseClassStatus*
0&nbsp;:&nbsp;停用,
1&nbsp;:&nbsp;启用|
|create_by|create_by|VARCHAR||null|create_by|
|create_date|create_date|DATETIME||null|create_date|
|update_by|update_by|VARCHAR||null|update_by|
|update_date|update_date|DATETIME||null|update_date|

##### 查询sql
> select  id,name,description,status,create_by,create_date,update_by,update_date from t_dict_parent_course_class




#### 表: t_dict_lecture  t_dict_lecture
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|名称|name|VARCHAR|**必填**|null|名称|
|描述|description|VARCHAR|**必填**|null|描述|
|status|status|INT||null|枚举赋值：*DictLectureStatus*
0&nbsp;:&nbsp;停用,
1&nbsp;:&nbsp;启用|
|create_by|create_by|VARCHAR||null|create_by|
|create_date|create_date|DATETIME||null|create_date|
|update_by|update_by|VARCHAR||null|update_by|
|update_date|update_date|DATETIME||null|update_date|

##### 查询sql
> select  id,name,description,status,create_by,create_date,update_by,update_date from t_dict_lecture




#### 表: t_dict_genre  t_dict_genre
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|id|id|VARCHAR|**必填**|null|id|
|名称|name|VARCHAR||null|名称|
|描述|description|VARCHAR||null|描述|
|图标|icon|VARCHAR||null|图标|
|btype|btype|INT||null|枚举赋值：*DictGenreBtype*
1&nbsp;:&nbsp;基础类,
2&nbsp;:&nbsp;个性类|
|status|status|INT||null|枚举赋值：*DictGenreStatus*
0&nbsp;:&nbsp;停用,
1&nbsp;:&nbsp;启用|
|create_by|create_by|VARCHAR||null|create_by|
|create_date|create_date|DATETIME||null|create_date|
|update_by|update_by|VARCHAR||null|update_by|
|update_date|update_date|DATETIME||null|update_date|
|编码，唯一|idcode|VARCHAR||null|编码，唯一|

##### 查询sql
> select  id,name,description,icon,btype,status,create_by,create_date,update_by,update_date,idcode from t_dict_genre




#### 表: t_course_word_quiz  t_course_word_quiz
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|导读课id|course_id|VARCHAR||null|导读课id|
|音频id|audio_id|VARCHAR||null|音频id|
|题目|question|VARCHAR|**必填**|null|题目|
|题目原文|question_original|VARCHAR||null|题目原文|
|question_type|question_type|INT|**必填**|null|枚举赋值：*CourseWordQuizQuestionType*
1&nbsp;:&nbsp;文字,
2&nbsp;:&nbsp;图片,
3&nbsp;:&nbsp;图文|
|img 题图|question_image|VARCHAR|**必填**|null|img 题图|
|顺序|indexs|INT|**必填**|null|顺序|
|分析|explication|VARCHAR|**必填**|null|分析|
|create_by|create_by|VARCHAR||null|create_by|
|create_date|create_date|DATETIME||null|create_date|
|update_by|update_by|VARCHAR||null|update_by|
|update_date|update_date|DATETIME||null|update_date|

##### 查询sql
> select  id,course_id,audio_id,question,question_original,question_type,question_image,indexs,explication,create_by,create_date,update_by,update_date from t_course_word_quiz




#### 表: t_course_word_detail  t_course_word_detail
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|课程id|course_id|VARCHAR|**必填**|null|课程id|
|介绍|introduce|VARCHAR|**必填**|null|介绍|

##### 查询sql
> select  course_id,introduce from t_course_word_detail




#### 表: t_course_word_book  t_course_word_book
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|词汇课id|course_id|VARCHAR||null|词汇课id|
|书单关联|book_ref|VARCHAR||null|书单关联|
|create_by|create_by|VARCHAR||null|create_by|
|create_date|create_date|DATETIME||null|create_date|

##### 查询sql
> select  id,course_id,book_ref,create_by,create_date from t_course_word_book




#### 表: t_course_word_audio  t_course_word_audio
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|导读课id|course_id|VARCHAR||null|导读课id|
|音频名称|audio_name|VARCHAR|**必填**|null|音频名称|
|大小|size|INT||null|大小|
|时长,秒|duration|INT|**必填**|null|时长,秒|
|与解锁顺序相关|course_index|INT||null|与解锁顺序相关|
|图片|show_img|VARCHAR||null|图片|
|file 存放url|store_url|VARCHAR|**必填**|null|file 存放url|
|词汇量|words_count|INT||null|词汇量|
|试听长度|trail_duration|INT||null|试听长度|
|create_by|create_by|VARCHAR||null|create_by|
|create_date|create_date|DATETIME||null|create_date|
|update_by|update_by|VARCHAR||null|update_by|
|update_date|update_date|DATETIME||null|update_date|

##### 查询sql
> select  id,course_id,audio_name,size,duration,course_index,show_img,store_url,words_count,trail_duration,create_by,create_date,update_by,update_date from t_course_word_audio




#### 表: t_course_word_answer  t_course_word_answer
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|题目id|question_id|VARCHAR|**必填**|null|题目id|
|答案内容|answer_content|VARCHAR|**必填**|null|答案内容|
|is_right|is_right|INT|**必填**|null|枚举赋值：*CourseWordAnswerIsRight*
0&nbsp;:&nbsp;不是,
1&nbsp;:&nbsp;是|
|create_by|create_by|VARCHAR||null|create_by|
|create_date|create_date|DATETIME||null|create_date|
|update_by|update_by|VARCHAR||null|update_by|
|update_date|update_date|DATETIME||null|update_date|

##### 查询sql
> select  id,question_id,answer_content,is_right,create_by,create_date,update_by,update_date from t_course_word_answer




#### 表: t_course_word  t_course_word
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|编号|course_no|VARCHAR||null|编号|
|名字|name|VARCHAR||null|名字|
|img 封面图|cover_img|VARCHAR||null|img 封面图|
|img 大图|pic_big|VARCHAR||null|img 大图|
|简介slogan|slogan|VARCHAR||null|简介slogan|
|描述|description|VARCHAR||null|描述|
|认知年级|grade|INT||null|认知年级|
|semester|semester|INT||null|枚举赋值：*CourseWordSemester*
1&nbsp;:&nbsp;上学期,
2&nbsp;:&nbsp;下学期|
|导读课数|course_total|INT||null|导读课数|
|词汇量|words_total|INT||null|词汇量|
|价格|price|INT|**必填**|null|价格|
|是否启用:1是0 否|status|INT||null|是否启用:1是0 否|
|create_by|create_by|VARCHAR||null|create_by|
|create_date|create_date|DATETIME||null|create_date|
|update_by|update_by|VARCHAR||null|update_by|
|update_date|update_date|DATETIME||null|update_date|

##### 查询sql
> select  id,course_no,name,cover_img,pic_big,slogan,description,grade,semester,course_total,words_total,price,status,create_by,create_date,update_by,update_date from t_course_word




#### 表: t_course_read_theme  t_course_read_theme
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|阅读课id|course_id|VARCHAR||null|阅读课id|
|主题id|theme_id|VARCHAR||null|主题id|

##### 查询sql
> select  id,course_id,theme_id from t_course_read_theme




#### 表: t_course_read_quiz  t_course_read_quiz
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|导读课id|course_id|VARCHAR||null|导读课id|
|题目|question|VARCHAR|**必填**|null|题目|
|题目原文，长内容|question_original|VARCHAR||null|题目原文，长内容|
|question_type|question_type|INT|**必填**|null|枚举赋值：*CourseReadQuizQuestionType*
1&nbsp;:&nbsp;文字,
2&nbsp;:&nbsp;图片,
3&nbsp;:&nbsp;图文|
|img 题图|question_image|VARCHAR||null|img 题图|
|题目解答|question_explain|VARCHAR|**必填**|null|题目解答|
|顺序|indexs|INT|**必填**|null|顺序|
|create_by|create_by|VARCHAR||null|create_by|
|create_date|create_date|DATETIME||null|create_date|
|update_by|update_by|VARCHAR||null|update_by|
|update_date|update_date|DATETIME||null|update_date|

##### 查询sql
> select  id,course_id,question,question_original,question_type,question_image,question_explain,indexs,create_by,create_date,update_by,update_date from t_course_read_quiz




#### 表: t_course_read_prefer_question  t_course_read_prefer_question
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|题目原文|question_original|VARCHAR||null|题目原文|
|题目|question|VARCHAR|**必填**|null|题目|
|is_multi|is_multi|INT|**必填**|null|是否多选0:不是,1:是|
|question_type|question_type|INT|**必填**|null|枚举赋值：*CourseReadPreferQuestionQuestionType*
1&nbsp;:&nbsp;文字,
2&nbsp;:&nbsp;图片,
3&nbsp;:&nbsp;图文|
|img 题图|question_image|VARCHAR||null|img 题图|
|顺序|indexs|INT|**必填**|null|顺序|
|create_by|create_by|VARCHAR||null|create_by|
|create_date|create_date|DATETIME||null|create_date|
|update_by|update_by|VARCHAR||null|update_by|
|update_date|update_date|DATETIME||null|update_date|
|status|status|INT|**必填**|null|枚举赋值：*CourseReadPreferQuestionStatus*
0&nbsp;:&nbsp;停用,
1&nbsp;:&nbsp;启用|

##### 查询sql
> select  id,question_original,question,is_multi,question_type,question_image,indexs,create_by,create_date,update_by,update_date,status from t_course_read_prefer_question




#### 表: t_course_read_prefer_answer  t_course_read_prefer_answer
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|题目id|question_id|VARCHAR||null|题目id|
|答案内容|answer_content|VARCHAR|**必填**|null|答案内容|
|create_by|create_by|VARCHAR||null|create_by|
|create_date|create_date|DATETIME||null|create_date|
|update_by|update_by|VARCHAR||null|update_by|
|update_date|update_date|DATETIME||null|update_date|

##### 查询sql
> select  id,question_id,answer_content,create_by,create_date,update_by,update_date from t_course_read_prefer_answer




#### 表: t_course_read_detail  t_course_read_detail
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|课程id|course_id|VARCHAR||null|课程id|
|介绍|introduce|VARCHAR|**必填**|null|介绍|

##### 查询sql
> select  course_id,introduce from t_course_read_detail




#### 表: t_course_read_book  t_course_read_book
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|阅读课id|course_id|VARCHAR||null|阅读课id|
|书单关联|book_ref|VARCHAR||null|书单关联|
|create_by|create_by|VARCHAR||null|create_by|
|create_date|create_date|DATETIME||null|create_date|

##### 查询sql
> select  id,course_id,book_ref,create_by,create_date from t_course_read_book




#### 表: t_course_read_audio  t_course_read_audio
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|阅读课id|course_id|VARCHAR||null|阅读课id|
|音频名称|audio_name|VARCHAR|**必填**|null|音频名称|
|时长,秒|duration|INT|**必填**|null|时长,秒|
|大小|size|VARCHAR|**必填**|null|大小|
|format|format|INT|**必填**|null|格式:0:mp3,1:MP3PRO2:WMA3:WAV,4:OGG,5:RM,6:REAL,7:APE,8:MODOLE,9:MIDI,10:VQF,11:ASF|
|图片|show_img|VARCHAR||null|图片|
|file 存放url|store_url|VARCHAR|**必填**|null|file 存放url|
|试听长度|trail_duration|INT||null|试听长度|
|与解锁顺序相关|course_index|INT||null|与解锁顺序相关|
|create_by|create_by|VARCHAR||null|create_by|
|create_date|create_date|DATETIME||null|create_date|
|update_by|update_by|VARCHAR||null|update_by|
|update_date|update_date|DATETIME||null|update_date|

##### 查询sql
> select  id,course_id,audio_name,duration,size,format,show_img,store_url,trail_duration,course_index,create_by,create_date,update_by,update_date from t_course_read_audio




#### 表: t_course_read_answer  t_course_read_answer
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|题目id|question_id|VARCHAR|**必填**|null|题目id|
|答案内容|answer_content|VARCHAR|**必填**|null|答案内容|
|is_right|is_right|INT|**必填**|null|枚举赋值：*CourseReadAnswerIsRight*
0&nbsp;:&nbsp;不是,
1&nbsp;:&nbsp;是|
|create_by|create_by|VARCHAR||null|create_by|
|create_date|create_date|DATETIME||null|create_date|
|update_by|update_by|VARCHAR||null|update_by|
|update_date|update_date|DATETIME||null|update_date|

##### 查询sql
> select  id,question_id,answer_content,is_right,create_by,create_date,update_by,update_date from t_course_read_answer




#### 表: t_course_read  t_course_read
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|编号|course_no|VARCHAR||null|编号|
|名字|name|VARCHAR||null|名字|
|img 封面图|cover_img|VARCHAR||null|img 封面图|
|img 详情图|img_big|VARCHAR||null|img 详情图|
|简介slogan|slogan|VARCHAR||null|简介slogan|
|简介|suggest_tip|VARCHAR||null|简介|
|体裁|genre_id|VARCHAR||null|体裁|
|BL值|bl_val|DECIMAL||null|BL值|
|show_type|show_type|INT||null|枚举赋值：*CourseReadShowType*
0&nbsp;:&nbsp;文字类,
1&nbsp;:&nbsp;图画类|
|推荐年级|suggest_grade|INT||null|推荐年级|
|导读课数|course_total|INT||null|导读课数|
|测试题数|quiz_question_total|INT||null|测试题数|
|阅读量|read_total|INT||null|阅读量|
|价格|price|DECIMAL||null|价格|
|status|status|INT||null|枚举赋值：*CourseReadStatus*
0&nbsp;:&nbsp;停用,
1&nbsp;:&nbsp;启用|
|create_by|create_by|VARCHAR||null|create_by|
|create_date|create_date|DATETIME||null|create_date|
|update_by|update_by|VARCHAR||null|update_by|
|update_date|update_date|DATETIME||null|update_date|

##### 查询sql
> select  id,course_no,name,cover_img,img_big,slogan,suggest_tip,genre_id,bl_val,show_type,suggest_grade,course_total,quiz_question_total,read_total,price,status,create_by,create_date,update_by,update_date from t_course_read




#### 表: t_course_parent_detail  t_course_parent_detail
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|课程id|course_id|VARCHAR||null|课程id|
|介绍|introduce|VARCHAR||null|介绍|

##### 查询sql
> select  course_id,introduce from t_course_parent_detail




#### 表: t_course_parent_audio  t_course_parent_audio
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|导读课id|course_id|VARCHAR||null|导读课id|
|名称|name|VARCHAR|**必填**|null|名称|
|时长,秒|duration|INT|**必填**|null|时长,秒|
|与解锁顺序相关|course_index|INT||null|与解锁顺序相关|
|大小|size|VARCHAR||null|大小|
|格式|format|INT||null|格式|
|图片|show_img|VARCHAR||null|图片|
|file 存放url|store_url|VARCHAR|**必填**|null|file 存放url|
|试听长度|trail_duration|INT||null|试听长度|
|create_by|create_by|VARCHAR||null|create_by|
|create_date|create_date|DATETIME||null|create_date|
|update_by|update_by|VARCHAR||null|update_by|
|update_date|update_date|DATETIME||null|update_date|

##### 查询sql
> select  id,course_id,name,duration,course_index,size,format,show_img,store_url,trail_duration,create_by,create_date,update_by,update_date from t_course_parent_audio




#### 表: t_course_parent  t_course_parent
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|编号|course_no|VARCHAR||null|编号|
|名字|name|VARCHAR|**必填**|null|名字|
|img 封面图|cover_img|VARCHAR|**必填**|null|img 封面图|
|img 详情图|img_big|VARCHAR||null|img 详情图|
|简介slogan|slogan|VARCHAR||null|简介slogan|
|分类|type_id|VARCHAR||null|分类|
|推荐理由|suggest_tip|VARCHAR||null|推荐理由|
|推荐年级起|suggest_grade_min|INT|**必填**|null|推荐年级起|
|推荐年级止|suggest_grade_max|INT|**必填**|null|推荐年级止|
|导读课数|course_total|INT||null|导读课数|
|测试题数|quiz_question_total|INT||null|测试题数|
|status|status|INT||null|枚举赋值：*CourseParentStatus*
0&nbsp;:&nbsp;停用,
1&nbsp;:&nbsp;启用|
|create_by|create_by|VARCHAR||null|create_by|
|create_date|create_date|DATETIME||null|create_date|
|update_by|update_by|VARCHAR||null|update_by|
|update_date|update_date|DATETIME||null|update_date|

##### 查询sql
> select  id,course_no,name,cover_img,img_big,slogan,type_id,suggest_tip,suggest_grade_min,suggest_grade_max,course_total,quiz_question_total,status,create_by,create_date,update_by,update_date from t_course_parent




#### 表: t_course_lecture_theme  t_course_lecture_theme
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|阅读课id|course_id|VARCHAR||null|阅读课id|
|主题id|theme_id|VARCHAR||null|主题id|

##### 查询sql
> select  id,course_id,theme_id from t_course_lecture_theme




#### 表: t_course_lecture_detail  t_course_lecture_detail
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|课程id|course_id|VARCHAR||null|课程id|
|介绍|introduce|VARCHAR||null|介绍|

##### 查询sql
> select  course_id,introduce from t_course_lecture_detail




#### 表: t_course_lecture_book  t_course_lecture_book
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|名家讲堂课id|lecture_id|VARCHAR||null|名家讲堂课id|
|书单关联|book_ref|VARCHAR||null|书单关联|
|create_by|create_by|VARCHAR||null|create_by|
|create_date|create_date|DATETIME||null|create_date|

##### 查询sql
> select  id,lecture_id,book_ref,create_by,create_date from t_course_lecture_book




#### 表: t_course_lecture_audio  t_course_lecture_audio
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|导读课id|course_id|VARCHAR||null|导读课id|
|名称|name|VARCHAR||null|名称|
|时长,秒|duration|INT||null|时长,秒|
|与解锁顺序相关|course_index|INT||null|与解锁顺序相关|
|大小|size|VARCHAR||null|大小|
|format|format|INT||null|格式//0:mp3,1:MP3PRO2:WMA3:WAV,4:OGG,5:RM,6:REAL,7:APE,8:MODOLE,9:MIDI,10:VQF,11:ASF|
|图片|show_img|VARCHAR||null|图片|
|file 存放url|store_url|VARCHAR||null|file 存放url|
|试听长度|trail_duration|INT||null|试听长度|
|create_by|create_by|VARCHAR||null|create_by|
|create_date|create_date|DATETIME||null|create_date|
|update_by|update_by|VARCHAR||null|update_by|
|update_date|update_date|DATETIME||null|update_date|

##### 查询sql
> select  id,course_id,name,duration,course_index,size,format,show_img,store_url,trail_duration,create_by,create_date,update_by,update_date from t_course_lecture_audio




#### 表: t_course_lecture  t_course_lecture
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|编号|course_no|VARCHAR||null|编号|
|名字|name|VARCHAR|**必填**|null|名字|
|img 封面图|cover_img|VARCHAR||null|img 封面图|
|img 详情图|img_big|VARCHAR||null|img 详情图|
|简介slogan|slogan|VARCHAR||null|简介slogan|
|推荐理由|suggest_tip|VARCHAR||null|推荐理由|
|分类id|type_id|VARCHAR||null|分类id|
|体裁|genre_id|VARCHAR||null|体裁|
|BL值|bl_val|DECIMAL||null|BL值|
|推荐年级起|suggest_grade_min|INT||null|推荐年级起|
|推荐年级止|suggest_grade_max|INT||null|推荐年级止|
|导读课数|course_total|INT||null|导读课数|
|测试题数|quiz_question_total|INT||null|测试题数|
|阅读量|read_total|INT||null|阅读量|
|主讲人|lecturer|VARCHAR||null|主讲人|
|主讲人介绍|lecturer_info|VARCHAR||null|主讲人介绍|
|价格|price|INT||null|价格|
|status|status|INT||null|枚举赋值：*CourseLectureStatus*
0&nbsp;:&nbsp;停用,
1&nbsp;:&nbsp;启用|
|create_by|create_by|VARCHAR||null|create_by|
|create_date|create_date|DATETIME||null|create_date|
|update_by|update_by|VARCHAR||null|update_by|
|update_date|update_date|DATETIME||null|update_date|

##### 查询sql
> select  id,course_no,name,cover_img,img_big,slogan,suggest_tip,type_id,genre_id,bl_val,suggest_grade_min,suggest_grade_max,course_total,quiz_question_total,read_total,lecturer,lecturer_info,price,status,create_by,create_date,update_by,update_date from t_course_lecture




#### 表: t_city  t_city
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|CITY_SN|CITY_SN|INT|**必填**|null||
|CITY_NAME|CITY_NAME|VARCHAR||null||
|PROVINCE_SN|PROVINCE_SN|INT||null|

##### 查询sql
> select  CITY_SN,CITY_NAME,PROVINCE_SN from t_city




#### 表: t_borough  t_borough
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|BOROUGH_SN|BOROUGH_SN|INT|**必填**|null||
|BOROUGH_NAME|BOROUGH_NAME|VARCHAR||null||
|CITY_SN|CITY_SN|INT||null|

##### 查询sql
> select  BOROUGH_SN,BOROUGH_NAME,CITY_SN from t_borough




#### 表: t_bk_ttr_year_blval  t_bk_ttr_year_blval
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|id|id|VARCHAR|**必填**|null||
|级别名称|name|VARCHAR||null|级别名称|
|选择年级|grade|VARCHAR||null|选择年级|
|选择时所在月份|month|VARCHAR||null|选择时所在月份|
|年级月份起始难度级别|grade_level|VARCHAR||null|年级月份起始难度级别|
|难度级别对应GL值|grade_bl|VARCHAR||null|难度级别对应GL值|
|备注|memo|VARCHAR||null|备注|
|create_date|create_date|DATETIME||null||

##### 查询sql
> select  id,name,grade,month,grade_level,grade_bl,memo,create_date from t_bk_ttr_year_blval




#### 表: t_bk_ttr_quiz_score_explianation  t_bk_ttr_quiz_score_explianation
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|id|id|VARCHAR|**必填**|null||
|name|name|VARCHAR||null|枚举赋值：*BkTtrQuizScoreExplianationName*
GK&nbsp;:&nbsp;GL|
|分值|grade|DECIMAL||null|分值|
|解释|explanation|VARCHAR||null|解释|

##### 查询sql
> select  id,name,grade,explanation from t_bk_ttr_quiz_score_explianation




#### 表: t_bk_ttr_quiz_questions  t_bk_ttr_quiz_questions
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|question_group|question_group|VARCHAR||null|试卷id，相同id为一次测试|
|题目id|question_id|VARCHAR||null|题目id|
|创建时间|create_date|DATETIME||null|创建时间|

##### 查询sql
> select  id,question_group,question_id,create_date from t_bk_ttr_quiz_questions




#### 表: t_bk_ttr_quiz_question_type  t_bk_ttr_quiz_question_type
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR||null|主键|
|题目id|question_id|VARCHAR||null|题目id|
|qtype|qtype|VARCHAR||null|枚举赋值：*BkTtrQuizQuestionTypeQtype*
W&nbsp;:&nbsp;字词识记（知识掌握）,
F&nbsp;:&nbsp;事实提取,
J&nbsp;:&nbsp;分析判断,
S&nbsp;:&nbsp;归纳与预测,
I&nbsp;:&nbsp;情绪感知|
|create_date|create_date|DATETIME||null|create_date|

##### 查询sql
> select  id,question_id,qtype,create_date from t_bk_ttr_quiz_question_type




#### 表: t_bk_ttr_quiz_question  t_bk_ttr_quiz_question
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|题目|question|VARCHAR||null|题目|
|题目原文，长内容|question_original|VARCHAR||null|题目原文，长内容|
|question_type|question_type|INT||null|枚举赋值：*BkTtrQuizQuestionQuestionType*
1&nbsp;:&nbsp;文字,
2&nbsp;:&nbsp;图片,
3&nbsp;:&nbsp;图文|
|img 题图|question_image|VARCHAR||null|img 题图|
|is_prepare|is_prepare|INT||null|枚举赋值：*BkTtrQuizQuestionIsPrepare*
0&nbsp;:&nbsp;不是,
1&nbsp;:&nbsp;是|
|难度分值|credit|DECIMAL||null|难度分值|
|阅读量|words_count|INT||null|阅读量|
|test_type|test_type|VARCHAR||null|枚举赋值：*BkTtrQuizQuestionTestType*
W&nbsp;:&nbsp;文学虚构类,
X&nbsp;:&nbsp;信息非虚构类|
|genre_text|genre_text|VARCHAR||null|枚举赋值：*BkTtrQuizQuestionGenreText*
DX&nbsp;:&nbsp;短文本虚构类,
DF&nbsp;:&nbsp;短文本非虚构,
CX&nbsp;:&nbsp;长文本虚构类,
CF&nbsp;:&nbsp;长文本非虚构|
|create_by|create_by|VARCHAR||null|create_by|
|create_date|create_date|DATETIME||null|create_date|
|update_by|update_by|VARCHAR||null|update_by|
|update_date|update_date|DATETIME||null|update_date|
|status|status|INT||null|枚举赋值：*BkTtrQuizQuestionStatus*
0&nbsp;:&nbsp;停用,
1&nbsp;:&nbsp;启用|
|编码，唯一|idcode|VARCHAR||null|编码，唯一|

##### 查询sql
> select  id,question,question_original,question_type,question_image,is_prepare,credit,words_count,test_type,genre_text,create_by,create_date,update_by,update_date,status,idcode from t_bk_ttr_quiz_question




#### 表: t_bk_ttr_quiz_level_meaning  t_bk_ttr_quiz_level_meaning
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|题目类型|type|VARCHAR|**必填**|null|题目类型|
|题目分值，难度值|score|DECIMAL|**必填**|null|题目分值，难度值|
|题目适用年级|grade|INT|**必填**|null|题目适用年级|
|分值代表含义|meaning|VARCHAR||null|分值代表含义|
|create_by|create_by|VARCHAR||null|create_by|
|create_date|create_date|DATETIME||null|create_date|
|update_by|update_by|VARCHAR||null|update_by|
|update_date|update_date|DATETIME||null|update_date|
|编码，唯一|idcode|VARCHAR||null|编码，唯一|

##### 查询sql
> select  id,type,score,grade,meaning,create_by,create_date,update_by,update_date,idcode from t_bk_ttr_quiz_level_meaning




#### 表: t_bk_ttr_quiz_answer  t_bk_ttr_quiz_answer
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|题目id|question_id|VARCHAR||null|题目id|
|答案内容|answer_content|VARCHAR|**必填**|null|答案内容|
|is_right|is_right|INT||null|枚举赋值：*BkTtrQuizAnswerIsRight*
0&nbsp;:&nbsp;不是,
1&nbsp;:&nbsp;是|
|create_by|create_by|VARCHAR||null|create_by|
|create_date|create_date|DATETIME||null|create_date|
|update_by|update_by|VARCHAR||null|update_by|
|update_date|update_date|DATETIME||null|update_date|
|编码，唯一|idcode|VARCHAR||null|编码，唯一|

##### 查询sql
> select  id,question_id,answer_content,is_right,create_by,create_date,update_by,update_date,idcode from t_bk_ttr_quiz_answer




#### 表: t_bk_ttr_quiz  t_bk_ttr_quiz
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|试卷id|quiz_id|VARCHAR|**必填**|null|试卷id|
|起始难度值|qziz_begin_val|DECIMAL|**必填**|null|起始难度值|
|完成难度值|qziz_finish_val|DECIMAL|**必填**|null|完成难度值|
|编码，唯一|idcode|VARCHAR||null|编码，唯一|
|create_date|create_date|DATETIME||null||

##### 查询sql
> select  id,quiz_id,qziz_begin_val,qziz_finish_val,idcode,create_date from t_bk_ttr_quiz




#### 表: t_bk_quiz_question  t_bk_quiz_question
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|书单id|book_id|VARCHAR||null|书单id|
|题目|question|VARCHAR||null|题目|
|题目原文，长内容|question_original|VARCHAR||null|题目原文，长内容|
|question_type|question_type|INT|**必填**|null|枚举赋值：*BkQuizQuestionQuestionType*
1&nbsp;:&nbsp;文字,
2&nbsp;:&nbsp;图片,
3&nbsp;:&nbsp;图文|
|img 题图|question_image|VARCHAR||null|img 题图|
|顺序|indexs|INT|**必填**|null|顺序|
|题目解答|question_explain|VARCHAR||null|题目解答|
|create_by|create_by|VARCHAR||null|create_by|
|create_date|create_date|DATETIME||null|create_date|
|update_by|update_by|VARCHAR||null|update_by|
|update_date|update_date|DATETIME||null|update_date|
|编码，唯一|idcode|VARCHAR||null|编码，唯一|

##### 查询sql
> select  id,book_id,question,question_original,question_type,question_image,indexs,question_explain,create_by,create_date,update_by,update_date,idcode from t_bk_quiz_question




#### 表: t_bk_quiz_answer  t_bk_quiz_answer
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|题目id|question_id|VARCHAR||null|题目id|
|答案内容|answer_content|VARCHAR||null|答案内容|
|is_right|is_right|INT|**必填**|null|枚举赋值：*BkQuizAnswerIsRight*
0&nbsp;:&nbsp;不是,
1&nbsp;:&nbsp;是|
|create_by|create_by|VARCHAR||null|create_by|
|create_date|create_date|DATETIME||null|create_date|
|update_by|update_by|VARCHAR||null|update_by|
|update_date|update_date|DATETIME||null|update_date|
|编码，唯一|idcode|VARCHAR||null|编码，唯一|

##### 查询sql
> select  id,question_id,answer_content,is_right,create_by,create_date,update_by,update_date,idcode from t_bk_quiz_answer




#### 表: t_bk_book_copy  t_bk_book_copy
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|编号|course_no|VARCHAR|**必填**|null|编号|
|名称|name|VARCHAR|**必填**|null|名称|
|img 封面图|cover_img|VARCHAR|**必填**|null|img 封面图|
|分类|type_id|VARCHAR|**必填**|null|分类|
|体裁|genre_id|VARCHAR|**必填**|null|体裁|
|阅读积分|read_points|INT|**必填**|null|阅读积分|
|BL值|bl_val|DECIMAL|**必填**|null|BL值|
|作者|author|VARCHAR|**必填**|null|作者|
|译者|translator|VARCHAR||null|译者|
|出版社|publisher|VARCHAR|**必填**|null|出版社|
|总字数|total|INT|**必填**|null|总字数|
|测试题目数|quiz_question_total|INT||null|测试题目数|
|认知年级起|grade_min|INT|**必填**|null|认知年级起|
|认知年级止|grade_max|INT|**必填**|null|认知年级止|
|简介|introduction|VARCHAR||null|简介|
|status|status|INT||null|枚举赋值：*BkBookCopyStatus*
0&nbsp;:&nbsp;下架,
1&nbsp;:&nbsp;上架|
|create_by|create_by|VARCHAR||null|create_by|
|create_date|create_date|DATETIME||null|create_date|
|update_by|update_by|VARCHAR||null|update_by|
|update_date|update_date|DATETIME||null|update_date|
|show_type|show_type|VARCHAR||null|枚举赋值：*BkBookCopyShowType*
PIC&nbsp;:&nbsp;图画书,
BRG&nbsp;:&nbsp;桥梁书,
PRI&nbsp;:&nbsp;初章书,
MID&nbsp;:&nbsp;中章书,
CHA&nbsp;:&nbsp;章节书|
|推荐理由|suggest_tip|VARCHAR||null|推荐理由|
|编码，唯一|idcode|VARCHAR||null|编码，唯一|

##### 查询sql
> select  id,course_no,name,cover_img,type_id,genre_id,read_points,bl_val,author,translator,publisher,total,quiz_question_total,grade_min,grade_max,introduction,status,create_by,create_date,update_by,update_date,show_type,suggest_tip,idcode from t_bk_book_copy




#### 表: t_bk_book  t_bk_book
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|编号|course_no|VARCHAR|**必填**|null|编号|
|名称|name|VARCHAR|**必填**|null|名称|
|img 封面图|cover_img|VARCHAR|**必填**|null|img 封面图|
|分类|type_id|VARCHAR|**必填**|null|分类|
|体裁|genre_id|VARCHAR|**必填**|null|体裁|
|阅读积分|read_points|INT|**必填**|null|阅读积分|
|BL值|bl_val|DECIMAL|**必填**|null|BL值|
|作者|author|VARCHAR|**必填**|null|作者|
|译者|translator|VARCHAR||null|译者|
|出版社|publisher|VARCHAR|**必填**|null|出版社|
|总字数|total|INT|**必填**|null|总字数|
|测试题目数|quiz_question_total|INT||null|测试题目数|
|认知年级起|grade_min|INT|**必填**|null|认知年级起|
|认知年级止|grade_max|INT|**必填**|null|认知年级止|
|简介|introduction|VARCHAR||null|简介|
|status|status|INT||null|枚举赋值：*BkBookStatus*
0&nbsp;:&nbsp;下架,
1&nbsp;:&nbsp;上架|
|create_by|create_by|VARCHAR||null|create_by|
|create_date|create_date|DATETIME||null|create_date|
|update_by|update_by|VARCHAR||null|update_by|
|update_date|update_date|DATETIME||null|update_date|
|show_type|show_type|VARCHAR||null|枚举赋值：*BkBookShowType*
PIC&nbsp;:&nbsp;图画书,
BRG&nbsp;:&nbsp;桥梁书,
PRI&nbsp;:&nbsp;初章书,
MID&nbsp;:&nbsp;中章书,
CHA&nbsp;:&nbsp;章节书|
|推荐理由|suggest_tip|VARCHAR||null|推荐理由|
|编码，唯一|idcode|VARCHAR||null|编码，唯一|

##### 查询sql
> select  id,course_no,name,cover_img,type_id,genre_id,read_points,bl_val,author,translator,publisher,total,quiz_question_total,grade_min,grade_max,introduction,status,create_by,create_date,update_by,update_date,show_type,suggest_tip,idcode from t_bk_book




#### 表: t_app_user_words_record  t_app_user_words_record
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|用户id|user_id|VARCHAR||null|用户id|
|课程id|course_id|VARCHAR||null|课程id|
|音频id|audio_id|VARCHAR||null|音频id|
|learn_statu|learn_statu|INT||null|枚举赋值：*AppUserWordsRecordLearnStatu*
1&nbsp;:&nbsp;未开始,
2&nbsp;:&nbsp;收听中,
3&nbsp;:&nbsp;已听完|
|日期|first_listened_date|DATETIME||null|日期|
|存储秒数|last_stop|INT||null|存储秒数|
|create_date|create_date|DATETIME||null|create_date|
|update_date|update_date|DATETIME||null|update_date|
|课程过期时间|overdue_date|DATETIME||null|课程过期时间|

##### 查询sql
> select  id,user_id,course_id,audio_id,learn_statu,first_listened_date,last_stop,create_date,update_date,overdue_date from t_app_user_words_record




#### 表: t_app_user_words_quiz_answer  t_app_user_words_quiz_answer
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|答题组次|answer_group|VARCHAR||null|答题组次|
|题目id|question_id|VARCHAR||null|题目id|
|回答答案|user_answer|VARCHAR||null|回答答案|
|回答时间|answer_time|DATETIME||null|回答时间|

##### 查询sql
> select  id,answer_group,question_id,user_answer,answer_time from t_app_user_words_quiz_answer




#### 表: t_app_user_words_quiz  t_app_user_words_quiz
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|用户id|user_id|VARCHAR||null|用户id|
|课程id|course_id|VARCHAR||null|课程id|
|答题时间|answer_date|DATETIME||null|答题时间|
|完成用时|finish_date|INT||null|完成用时|
|首次正确率|first_ratio|DECIMAL||null|首次正确率|
|阅读量|total|INT||null|阅读量|
|获得蓝钻数量|num|INT||null|获得蓝钻数量|
|创建时间
|create_date|DATETIME||null|创建时间
|
|音频id|audio_id|VARCHAR||null|音频id|
|status|status|VARCHAR||null|是否更正 0：为更正 1：以更正|
|quiz_result|quiz_result|VARCHAR||null|是否完成 0：未完成 。1：已完成|

##### 查询sql
> select  id,user_id,course_id,answer_date,finish_date,first_ratio,total,num,create_date,audio_id,status,quiz_result from t_app_user_words_quiz




#### 表: t_app_user_words_favourites  t_app_user_words_favourites
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|用户id|user_id|VARCHAR||null|用户id|
|课程id|course_id|VARCHAR||null|课程id|
|favourite_statu|favourite_statu|INT||null|枚举赋值：*AppUserWordsFavouritesFavouriteStatu*
1&nbsp;:&nbsp;收藏中,
2&nbsp;:&nbsp;移除收藏|
|首次收藏时间|create_date|DATETIME||null|首次收藏时间|
|update_date|update_date|DATETIME||null|update_date|

##### 查询sql
> select  id,user_id,course_id,favourite_statu,create_date,update_date from t_app_user_words_favourites




#### 表: t_app_user_ttr_result_record  t_app_user_ttr_result_record
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|用户id|user_id|VARCHAR||null|用户id|
|测试id|quiz_id|VARCHAR||null|测试id|
|测试组次|quiz_group|INT||null|测试组次|
|ER值|er_val|DECIMAL||null|ER值|
|阅读能力|read_ability|VARCHAR||null|阅读能力|
|词汇值|words|INT||null|词汇值|
|逻辑能力|logical_ability|VARCHAR||null|逻辑能力|
|推理能力|suggest_ability|VARCHAR||null|推理能力|
|测试时间|quiz_time|DATETIME||null|测试时间|
|quiz_status|quiz_status|INT||null|枚举赋值：*AppUserTtrResultRecordQuizStatus*
1&nbsp;:&nbsp;未开始,
2&nbsp;:&nbsp;进行中,
3&nbsp;:&nbsp;暂停,
4&nbsp;:&nbsp;超时,
5&nbsp;:&nbsp;完成|
|测试报告|quiz_report|VARCHAR||null|测试报告|
|推送机构时间|push_org_time|DATETIME||null|推送机构时间|
|机构首次查看时间|first_read_time|DATETIME||null|机构首次查看时间|
|推送用户时间|push_user_time|DATETIME||null|推送用户时间|
|用户首次查看时间|firsr_read_time|DATETIME||null|用户首次查看时间|

##### 查询sql
> select  id,user_id,quiz_id,quiz_group,er_val,read_ability,words,logical_ability,suggest_ability,quiz_time,quiz_status,quiz_report,push_org_time,first_read_time,push_user_time,firsr_read_time from t_app_user_ttr_result_record




#### 表: t_app_user_ttr_report  t_app_user_ttr_report
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|用户id|user_id|VARCHAR||null|用户id|
|测试时间|quiz_time|DATETIME||null|测试时间|
|测试记录表id|quiz_id|VARCHAR||null|测试记录表id|
|quiz_result|quiz_result|INT||null|枚举赋值：*AppUserTtrReportQuizResult*
0&nbsp;:&nbsp;未完成,
1&nbsp;:&nbsp;完成|
|create_date|create_date|DATETIME||null|create_date|
|当前级别|gl|VARCHAR||null|当前级别|
|gl提示信息|gl_xx|VARCHAR||null|gl提示信息|
|测试卡id|card_id|VARCHAR||null|测试卡id|
|文学-事实提取分数|wx_tq|VARCHAR||null|文学-事实提取分数|
|文学_分析判断分数|wx_fx|VARCHAR||null|文学_分析判断分数|
|文学_归纳与预测分数|wx_gn|VARCHAR||null|文学_归纳与预测分数|
|wx_wb|wx_wb|VARCHAR||null|文学_文本复杂与适应性分数|
|文学_情绪感知分数|wx_qx|VARCHAR||null|文学_情绪感知分数|
|信息_事实提取分数|xx_tq|VARCHAR||null|信息_事实提取分数|
|信息_分析判断分数|xx_fx|VARCHAR||null|信息_分析判断分数|
|信息_归纳与预测分数|xx_gn|VARCHAR||null|信息_归纳与预测分数|
|xx_wb|xx_wb|VARCHAR||null|信息_文本复杂与适应性分数|
|zpd 最小值|zpd_min|VARCHAR||null|zpd 最小值|
|zpd 最大值|zpd_max|VARCHAR||null|zpd 最大值|
|字词识别和运用|jc_zc|VARCHAR||null|字词识别和运用|
|阅读速度|jc_yd|VARCHAR||null|阅读速度|
|学习包json|good_extend|TEXT||null|学习包json|
|测试时所处年级的gl|current_grade|VARCHAR||null|测试时所处年级的gl|

##### 查询sql
> select  id,user_id,quiz_time,quiz_id,quiz_result,create_date,gl,gl_xx,card_id,wx_tq,wx_fx,wx_gn,wx_wb,wx_qx,xx_tq,xx_fx,xx_gn,xx_wb,zpd_min,zpd_max,jc_zc,jc_yd,good_extend,current_grade from t_app_user_ttr_report




#### 表: t_app_user_ttr_record  t_app_user_ttr_record
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|id|id|VARCHAR|**必填**|null||
|测试卡测试id|test_id|VARCHAR||null|测试卡测试id|
|用戶id|user_id|VARCHAR||null|用戶id|
|status|status|VARCHAR||null|答题状态 0 ：未完成，进行中 1:已完成 2:暂停 3：终止测试|
|答题剩余时间（秒）|residue_time|INT||null|答题剩余时间（秒）|
|用户答题记录|grade_quiz|TEXT||null|用户答题记录|
|type|type|VARCHAR||null|0:热身题、1：定性测试、2：三叁测试套题、3：偏好测试|
|创建人|create_by|VARCHAR||null|创建人|
|更新时间|update_date|DATETIME||null|更新时间|
|更新人|update_by|VARCHAR||null|更新人|
|套题id|questions_id|VARCHAR||null|套题id|
|创建时间|create_date|DATETIME||null|创建时间|

##### 查询sql
> select  id,test_id,user_id,status,residue_time,grade_quiz,type,create_by,update_date,update_by,questions_id,create_date from t_app_user_ttr_record




#### 表: t_app_user_ttr_pre_record  t_app_user_ttr_pre_record
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|id|id|VARCHAR|**必填**|null||
|用户id|user_id|VARCHAR||null|用户id|
|status|status|VARCHAR||null|答题状态 0 ：未完成 1:已完成 2:暂停
            |
|is_stop|is_stop|VARCHAR||null|是否终止（0：未终止 1：已终止）|
|创建时间|create_date|DATETIME||null|创建时间|
|创建人|create_by|VARCHAR||null|创建人|
|更新时间|update_date|DATETIME||null|更新时间|
|更新人|update_by|VARCHAR||null|更新人|
|del_flag|del_flag|VARCHAR||null|删除标记 0：未删除，1：已删除|

##### 查询sql
> select  id,user_id,status,is_stop,create_date,create_by,update_date,update_by,del_flag from t_app_user_ttr_pre_record




#### 表: t_app_user_ttr_pre_answer  t_app_user_ttr_pre_answer
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|测试报告id|quiz_report_id|VARCHAR||null|测试报告id|
|测试题目id|quiz_question_id|VARCHAR||null|测试题目id|
|回答答案|user_answer|VARCHAR||null|回答答案|
|回答时间|answer_time|DATETIME||null|回答时间|
|回答用时|taking|INT||null|回答用时|
|create_date|create_date|DATETIME||null||

##### 查询sql
> select  id,quiz_report_id,quiz_question_id,user_answer,answer_time,taking,create_date from t_app_user_ttr_pre_answer




#### 表: t_app_user_ttr_answer  t_app_user_ttr_answer
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|测试报告id|quiz_report_id|VARCHAR||null|测试报告id|
|测试题目id|quiz_question_id|VARCHAR||null|测试题目id|
|回答答案|user_answer|VARCHAR||null|回答答案|
|回答时间|answer_time|DATETIME||null|回答时间|
|回答用时|taking|INT||null|回答用时|
|用户id|user_id|VARCHAR||null|用户id|
|用户当前级别|grade|VARCHAR||null|用户当前级别|
|创建时间
|create_date|DATETIME||null|创建时间
|
|套题id|quiz_questions_id|VARCHAR||null|套题id|
|type|type|INT||null|是否是套题 0：不是 1：是|

##### 查询sql
> select  id,quiz_report_id,quiz_question_id,user_answer,answer_time,taking,user_id,grade,create_date,quiz_questions_id,type from t_app_user_ttr_answer




#### 表: t_app_user_theme_prefer  t_app_user_theme_prefer
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|用户id|user_id|VARCHAR||null|用户id|
|偏好编号|prefer_id|VARCHAR||null|偏好编号|
|提交时所在次序|commit_index|INT||null|提交时所在次序|
|create_by|create_by|VARCHAR||null|create_by|
|create_date|create_date|DATETIME||null|create_date|
|update_by|update_by|VARCHAR||null|update_by|
|update_date|update_date|DATETIME||null|update_date|
|del_flag|del_flag|INT||null|枚举赋值：*AppUserThemePreferDelFlag*
0&nbsp;:&nbsp;可用,
1&nbsp;:&nbsp;封禁|

##### 查询sql
> select  id,user_id,prefer_id,commit_index,create_by,create_date,update_by,update_date,del_flag from t_app_user_theme_prefer




#### 表: t_app_user_summarize  t_app_user_summarize
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|user_id|user_id|VARCHAR|**必填**|null|与app user 表id一致|
|阅读量|read_total|INT||null|阅读量|
|阅读量更新时间|read_total_updatetime|DATETIME||null|阅读量更新时间|
|词汇量|words_total|INT||null|词汇量|
|词汇量更新时间|words_total_updatetime|DATETIME||null|词汇量更新时间|
|学习时长|learn_total|INT||null|学习时长|
|学习时长更新时间|learn_updatetime|DATETIME||null|学习时长更新时间|
|读书量|book_total|INT||null|读书量|
|读书量更新时间|book_total_updatetime|DATETIME||null|读书量更新时间|
|课程量|course_total|INT||null|课程量|
|课程量更新时间|course_total_updatetime|DATETIME||null|课程量更新时间|
|create_date|create_date|DATETIME||null||

##### 查询sql
> select  user_id,read_total,read_total_updatetime,words_total,words_total_updatetime,learn_total,learn_updatetime,book_total,book_total_updatetime,course_total,course_total_updatetime,create_date from t_app_user_summarize




#### 表: t_app_user_reading_record  t_app_user_reading_record
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|用户id|user_id|VARCHAR||null|用户id|
|课程id|course_id|VARCHAR||null|课程id|
|音频id|audio_id|VARCHAR||null|音频id|
|learn_statu|learn_statu|INT||null|枚举赋值：*AppUserReadingRecordLearnStatu*
1&nbsp;:&nbsp;未开始,
2&nbsp;:&nbsp;收听中,
3&nbsp;:&nbsp;已听完|
|首次收听日期|first_listened_date|DATETIME||null|首次收听日期|
|存储秒数|last_stop|INT||null|存储秒数|
|create_date|create_date|DATETIME||null|create_date|
|update_date|update_date|DATETIME||null|update_date|
|课程过期时间|overdue_date|DATETIME||null|课程过期时间|

##### 查询sql
> select  id,user_id,course_id,audio_id,learn_statu,first_listened_date,last_stop,create_date,update_date,overdue_date from t_app_user_reading_record




#### 表: t_app_user_reading_quiz_answer  t_app_user_reading_quiz_answer
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|答题记录id|answer_group|VARCHAR||null|答题记录id|
|题目id|question_id|VARCHAR||null|题目id|
|回答答案|user_answer|VARCHAR||null|回答答案|
|回答时间|answer_time|DATETIME||null|回答时间|

##### 查询sql
> select  id,answer_group,question_id,user_answer,answer_time from t_app_user_reading_quiz_answer




#### 表: t_app_user_reading_quiz  t_app_user_reading_quiz
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|用户id|user_id|VARCHAR||null|用户id|
|课程id|course_id|VARCHAR||null|课程id|
|答题时间
|answer_date|DATETIME||null|答题时间
|
|完成时间
|finish_date|INT||null|完成时间
|
|create_date|create_date|DATETIME||null|create_date|
|正确率
|first_ratio|DECIMAL||null|正确率
|
|阅读量|total|INT||null|阅读量|
|获得蓝钻数量|num|INT||null|获得蓝钻数量|
|status|status|VARCHAR||null|是否更正 0：为更正 1：以更正|
|quiz_result|quiz_result|VARCHAR||null|枚举赋值：*AppUserReadingQuizQuizResult*
0&nbsp;:&nbsp;未完成,
1&nbsp;:&nbsp;完成|

##### 查询sql
> select  id,user_id,course_id,answer_date,finish_date,create_date,first_ratio,total,num,status,quiz_result from t_app_user_reading_quiz




#### 表: t_app_user_reading_favourites  t_app_user_reading_favourites
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|用户id|user_id|VARCHAR||null|用户id|
|课程id|course_id|VARCHAR||null|课程id|
|favourite_statu|favourite_statu|INT||null|枚举赋值：*AppUserReadingFavouritesFavouriteStatu*
1&nbsp;:&nbsp;收藏中,
2&nbsp;:&nbsp;移除收藏|
|首次收藏时间|create_date|DATETIME||null|首次收藏时间|
|update_date|update_date|DATETIME||null|update_date|

##### 查询sql
> select  id,user_id,course_id,favourite_statu,create_date,update_date from t_app_user_reading_favourites




#### 表: t_app_user_read_prefer_quiz_answer  t_app_user_read_prefer_quiz_answer
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|用户id|user_id|VARCHAR||null|用户id|
|题目id|question_id|VARCHAR||null|题目id|
|回答内容|answer_content|VARCHAR||null|回答内容|
|答题时间|answer_date|DATETIME||null|答题时间|
|完成用时|finish_date|INT||null|完成用时|

##### 查询sql
> select  id,user_id,question_id,answer_content,answer_date,finish_date from t_app_user_read_prefer_quiz_answer




#### 表: t_app_user_quizcard  t_app_user_quizcard
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|id|id|VARCHAR|**必填**|null||
|user_id|user_id|VARCHAR||null|
|card_no|card_no|VARCHAR||null|测试卡号,随机10位数字字符混合串，
            唯一，
            课根据用户id，订单id生成，后缀生次序号：ZvctrIQG01，ZvctrIQG02
            |
|get_type|get_type|INT||null|枚举赋值：*AppUserQuizcardGetType*
1&nbsp;:&nbsp;单独购买,
2&nbsp;:&nbsp;学习包购买,
3&nbsp;:&nbsp;兑换,
4&nbsp;:&nbsp;赠与补偿,
5&nbsp;:&nbsp;其他|
|order_id|order_id|VARCHAR||null|
|起始日期|date_available|DATE||null|起始日期|
|截至日期|date_expiry|DATE||null|截至日期|
|create_date|create_date|DATETIME||null|create_date|
|status|status|INT||null|0 ：未使用、1：以使用、2:已过期；|

##### 查询sql
> select  id,user_id,card_no,get_type,order_id,date_available,date_expiry,create_date,status from t_app_user_quizcard




#### 表: t_app_user_pushtoken  t_app_user_pushtoken
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|id|id|VARCHAR|**必填**|null|id|
|极光推送token|pushtoken|VARCHAR||null|极光推送token|
|user_id|user_id|VARCHAR||null|
|ptype|ptype|VARCHAR||null|枚举赋值：*AppUserPushtokenPtype*
M&nbsp;:&nbsp;ios,
A&nbsp;:&nbsp;android|
|devicetoken|devicetoken|VARCHAR||null|ios设备推送token|
|create_date|create_date|DATETIME||null|create_date|
|show_statu|show_statu|INT|**必填**|null|枚举赋值：*AppUserPushtokenShowStatu*
0&nbsp;:&nbsp;未生效,
1&nbsp;:&nbsp;使用中|

##### 查询sql
> select  id,pushtoken,user_id,ptype,devicetoken,create_date,show_statu from t_app_user_pushtoken




#### 表: t_app_user_points_record  t_app_user_points_record
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|用户id|user_id|VARCHAR||null|用户id|
|任务id|task_id|VARCHAR||null|任务id|
|获取时间|gain_date|DATETIME||null|获取时间|
|备注|memo|VARCHAR||null|备注|
|获取积分值|points|INT||null|获取积分值|
|当时的总积分记录|current_integral|INT||null|当时的总积分记录|

##### 查询sql
> select  id,user_id,task_id,gain_date,memo,points,current_integral from t_app_user_points_record




#### 表: t_app_user_order_pay_record  t_app_user_order_pay_record
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|订单表id|order_id|VARCHAR||null|订单表id|
|订单流水号|order_sequence|VARCHAR||null|订单流水号|
|支付方式|pay_type|VARCHAR||null|支付方式|
|支付流水号|pay_sequence|VARCHAR||null|支付流水号|
|支付时间|pay_time|DATETIME||null|支付时间|
|pay_statu|pay_statu|INT||null|枚举赋值：*AppUserOrderPayRecordPayStatu*
0&nbsp;:&nbsp;待支付,
1&nbsp;:&nbsp;已支付 2&nbsp;:&nbsp;已取消|
|支付状态更新时间|pay_statu_updatetime|DATETIME||null|支付状态更新时间|
|备注|memo|VARCHAR||null|备注|

##### 查询sql
> select  id,order_id,order_sequence,pay_type,pay_sequence,pay_time,pay_statu,pay_statu_updatetime,memo from t_app_user_order_pay_record




#### 表: t_app_user_order  t_app_user_order
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|订单流水号|order_sequence|VARCHAR||null|订单流水号|
|用户id|user_id|VARCHAR||null|用户id|
|商品id|commodity_id|VARCHAR||null|商品id|
|type|type|INT||null|枚举赋值：*AppUserOrderType*
1&nbsp;:&nbsp;阅读课,
2&nbsp;:&nbsp;词汇课,
3&nbsp;:&nbsp;学习包,
4&nbsp;:&nbsp;测试卡,
5&nbsp;:&nbsp;vip年卡,
6&nbsp;:&nbsp;名家讲堂|
|生成时间|order_create_date|DATETIME||null|生成时间|
|pay_statu|pay_statu|INT||null|枚举赋值：*AppUserOrderPayStatu*
0&nbsp;:&nbsp;待支付,
1&nbsp;:&nbsp;已支付 2&nbsp;:&nbsp;已取消|
|支付状态更新时间|pay_statu_updatetime|DATETIME||null|支付状态更新时间|
|create_date|create_date|DATETIME||null|create_date|
|update_date|update_date|DATETIME||null|update_date|
|报告id|report_id|VARCHAR||null|报告id|

##### 查询sql
> select  id,order_sequence,user_id,commodity_id,type,order_create_date,pay_statu,pay_statu_updatetime,create_date,update_date,report_id from t_app_user_order




#### 表: t_app_user_membership  t_app_user_membership
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|id|id|VARCHAR|**必填**|null||
|user_id|user_id|VARCHAR||null|
|get_type|get_type|INT||null|枚举赋值：*AppUserMembershipGetType*
1&nbsp;:&nbsp;单独购买,
2&nbsp;:&nbsp;学习包购买,
3&nbsp;:&nbsp;兑换,
4&nbsp;:&nbsp;赠与补偿,
5&nbsp;:&nbsp;其他|
|order_id|order_id|VARCHAR||null|
|起始日期|date_available|DATE||null|起始日期|
|截至日期|date_expiry|DATE||null|截至日期|
|create_date|create_date|DATETIME||null|create_date|

##### 查询sql
> select  id,user_id,get_type,order_id,date_available,date_expiry,create_date from t_app_user_membership




#### 表: t_app_user_lecture_record  t_app_user_lecture_record
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|用户id|user_id|VARCHAR||null|用户id|
|课程id|course_id|VARCHAR||null|课程id|
|音频id|audio_id|VARCHAR||null|音频id|
|learn_statu|learn_statu|INT||null|枚举赋值：*AppUserLectureRecordLearnStatu*
1&nbsp;:&nbsp;未开始,
2&nbsp;:&nbsp;收听中,
3&nbsp;:&nbsp;已听完|
|日期|first_listened_date|DATETIME||null|日期|
|存储秒数|last_stop|INT||null|存储秒数|
|create_date|create_date|DATETIME||null|create_date|
|update_date|update_date|DATETIME||null|update_date|
|课程过期时间|overdue_date|DATETIME||null|课程过期时间|

##### 查询sql
> select  id,user_id,course_id,audio_id,learn_statu,first_listened_date,last_stop,create_date,update_date,overdue_date from t_app_user_lecture_record




#### 表: t_app_user_lecture_favourites  t_app_user_lecture_favourites
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|用户id|user_id|VARCHAR||null|用户id|
|课程id|course_id|VARCHAR||null|课程id|
|favourite_statu|favourite_statu|INT||null|枚举赋值：*AppUserLectureFavouritesFavouriteStatu*
1&nbsp;:&nbsp;收藏中,
2&nbsp;:&nbsp;移除收藏|
|首次收藏时间|create_date|DATETIME||null|首次收藏时间|
|update_date|update_date|DATETIME||null|update_date|

##### 查询sql
> select  id,user_id,course_id,favourite_statu,create_date,update_date from t_app_user_lecture_favourites




#### 表: t_app_user_info  t_app_user_info
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键，与用户表一致|id|VARCHAR|**必填**|null|主键，与用户表一致|
|姓名|name|VARCHAR||null|姓名|
|img photo|photo|VARCHAR||null|img photo|
|注册时的年级|reg_grade|VARCHAR|**必填**|null|注册时的年级|
|当前年级|current_grade|VARCHAR|**必填**|null|当前年级|
|gender|gender|INT||null|性别0:女 1:男    -1：未知|
|出生日期|birthday|DATE||null|出生日期|
|地址省|province|VARCHAR||null|地址省|
|地址市|city|VARCHAR||null|地址市|
|地址区|district|VARCHAR||null|地址区|
|GL值|cl_val|DECIMAL||null|GL值|
|GL更新时间|gl_update_date|DATETIME||null|GL更新时间|
|积分|points|INT||null|积分|
|积分更新时间|points_updatetime|DATETIME||null|积分更新时间|
|订单总金额,分|order_total|INT||null|订单总金额,分|
|总金额更新时间|total_updatetime|DATETIME||null|总金额更新时间|
|注册时间|reg_time|DATETIME||null|注册时间|
|注册时所在机构|reg_organization|VARCHAR||null|注册时所在机构|
|当前所在结构|current_organization|VARCHAR||null|当前所在结构|
|member_status|member_status|VARCHAR||null|枚举赋值：*AppUserInfoMemberStatus*
0&nbsp;:&nbsp;停用,
1&nbsp;:&nbsp;启用|
|会员状态更新时间|member_updatetime|DATETIME||null|会员状态更新时间|
|会籍起始日期|member_available|DATE||null|会籍起始日期|
|会籍截至日期|member_expiry|DATE||null|会籍截至日期|
|创建人|create_by|VARCHAR||null|创建人|
|创建时间|create_date|DATETIME||null|创建时间|
|更新人|update_by|VARCHAR||null|更新人|
|更新时间|update_date|DATETIME||null|更新时间|
|vip_status|vip_status|INT||null|是否是vip 0：不是 1：是|
|邮箱|email|VARCHAR||null|邮箱|
|uid_qq|uid_qq|VARCHAR||null||
|uid_wechat|uid_wechat|VARCHAR||null||
|uid_sina|uid_sina|VARCHAR||null||
|is_remind|is_remind|INT||null|是否提醒修改密码  0:未提醒   1:已提醒|

##### 查询sql
> select  id,name,photo,reg_grade,current_grade,gender,birthday,province,city,district,cl_val,gl_update_date,points,points_updatetime,order_total,total_updatetime,reg_time,reg_organization,current_organization,member_status,member_updatetime,member_available,member_expiry,create_by,create_date,update_by,update_date,vip_status,email,uid_qq,uid_wechat,uid_sina,is_remind from t_app_user_info




#### 表: t_app_user_group_ref  t_app_user_group_ref
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|id|id|VARCHAR|**必填**|null|id|
|组id|group_id|VARCHAR||null|组id|
|用户id|user_id|VARCHAR||null|用户id|
|创建人|create_by|VARCHAR||null|创建人|
|创建时间|create_date|DATETIME||null|创建时间|

##### 查询sql
> select  id,group_id,user_id,create_by,create_date from t_app_user_group_ref




#### 表: t_app_user_group  t_app_user_group
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|id|id|VARCHAR|**必填**|null|id|
|名称|name|VARCHAR||null|名称|
|分组code|gcode|VARCHAR||null|分组code|
|备注|memo|VARCHAR||null|备注|
|创建人|create_by|VARCHAR||null|创建人|
|创建时间|create_date|DATETIME||null|创建时间|

##### 查询sql
> select  id,name,gcode,memo,create_by,create_date from t_app_user_group




#### 表: t_app_user_feedback  t_app_user_feedback
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|user_id|user_id|VARCHAR||null|用户id，app用户id|
|反馈意见|feedback|VARCHAR||null|反馈意见|
|提交时间|commit_time|DATETIME||null|提交时间|
|reply_user_id|reply_user_id|VARCHAR||null|反馈人id，平台用户id|
|反馈内容|reply|VARCHAR||null|反馈内容|
|反馈时间|reply_time|DATETIME||null|反馈时间|
|reply_statu|reply_statu|INT||null|枚举赋值：*AppUserFeedbackReplyStatu*
1&nbsp;:&nbsp;未反馈,
2&nbsp;:&nbsp;已反馈|
|notified|notified|INT||null|枚举赋值：*AppUserFeedbackNotified*
0&nbsp;:&nbsp;不是,
1&nbsp;:&nbsp;是|
|notification_type|notification_type|INT||null|枚举赋值：*AppUserFeedbackNotificationType*
1&nbsp;:&nbsp;推送,
2&nbsp;:&nbsp;短信,
3&nbsp;:&nbsp;私信,
4&nbsp;:&nbsp;通知|
|通知时间|notice_date|DATETIME||null|通知时间|

##### 查询sql
> select  id,user_id,feedback,commit_time,reply_user_id,reply,reply_time,reply_statu,notified,notification_type,notice_date from t_app_user_feedback




#### 表: t_app_user_course_word  t_app_user_course_word
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|id|id|VARCHAR|**必填**|null||
|user_id|user_id|VARCHAR||null|
|课程id|card_no|VARCHAR||null|课程id|
|get_type|get_type|INT||null|枚举赋值：*AppUserCourseWordGetType*
1&nbsp;:&nbsp;单独购买,
2&nbsp;:&nbsp;学习包购买,
3&nbsp;:&nbsp;兑换,
4&nbsp;:&nbsp;赠与补偿,
5&nbsp;:&nbsp;其他|
|order_id|order_id|VARCHAR||null|
|起始日期|date_available|DATE||null|起始日期|
|截至日期|date_expiry|DATE||null|截至日期|
|create_date|create_date|DATETIME||null|create_date|

##### 查询sql
> select  id,user_id,card_no,get_type,order_id,date_available,date_expiry,create_date from t_app_user_course_word




#### 表: t_app_user_course_read  t_app_user_course_read
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|id|id|VARCHAR|**必填**|null||
|user_id|user_id|VARCHAR||null|
|课程id|card_no|VARCHAR||null|课程id|
|get_type|get_type|INT||null|枚举赋值：*AppUserCourseReadGetType*
1&nbsp;:&nbsp;单独购买,
2&nbsp;:&nbsp;学习包购买,
3&nbsp;:&nbsp;兑换,
4&nbsp;:&nbsp;赠与补偿,
5&nbsp;:&nbsp;其他|
|order_id|order_id|VARCHAR||null|
|起始日期|date_available|DATE||null|起始日期|
|截至日期|date_expiry|DATE||null|截至日期|
|create_date|create_date|DATETIME||null|create_date|

##### 查询sql
> select  id,user_id,card_no,get_type,order_id,date_available,date_expiry,create_date from t_app_user_course_read




#### 表: t_app_user_course_lecture  t_app_user_course_lecture
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|id|id|VARCHAR|**必填**|null||
|user_id|user_id|VARCHAR||null|
|课程id|card_no|VARCHAR||null|课程id|
|get_type|get_type|INT||null|枚举赋值：*AppUserCourseLectureGetType*
1&nbsp;:&nbsp;单独购买,
2&nbsp;:&nbsp;学习包购买,
3&nbsp;:&nbsp;兑换,
4&nbsp;:&nbsp;赠与补偿,
5&nbsp;:&nbsp;其他|
|order_id|order_id|VARCHAR||null|
|起始日期|date_available|DATE||null|起始日期|
|截至日期|date_expiry|DATE||null|截至日期|
|创建时间|create_date|DATETIME||null|创建时间|

##### 查询sql
> select  id,user_id,card_no,get_type,order_id,date_available,date_expiry,create_date from t_app_user_course_lecture




#### 表: t_app_user_bookshelf  t_app_user_bookshelf
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|用户id|user_id|VARCHAR||null|用户id|
|书单id|book_id|VARCHAR||null|书单id|
|status|status|INT||null|枚举赋值：*AppUserBookshelfStatus*
0&nbsp;:&nbsp;停用,
1&nbsp;:&nbsp;启用|
|create_date|create_date|DATETIME||null|create_date|
|update_date|update_date|DATETIME||null|update_date|

##### 查询sql
> select  id,user_id,book_id,status,create_date,update_date from t_app_user_bookshelf




#### 表: t_app_user_bl_quiz_answer  t_app_user_bl_quiz_answer
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|测试报告id|quiz_report_id|VARCHAR||null|测试报告id|
|测试题目id|quiz_question_id|VARCHAR||null|测试题目id|
|回答答案|user_answer|VARCHAR||null|回答答案|
|回答时间|answer_time|DATETIME||null|回答时间|

##### 查询sql
> select  id,quiz_report_id,quiz_question_id,user_answer,answer_time from t_app_user_bl_quiz_answer




#### 表: t_app_user_bl_quiz  t_app_user_bl_quiz
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|用户id|user_id|VARCHAR||null|用户id|
|测试时间|quiz_time|DATETIME||null|测试时间|
|测试id|quiz_id|VARCHAR||null|测试id|
|quiz_result|quiz_result|VARCHAR||null|枚举赋值：*AppUserBlQuizQuizResult*
0&nbsp;:&nbsp;未完成,
1&nbsp;:&nbsp;完成|
|正确率|first_ratio|DECIMAL||null|正确率|
|阅读量|total|INT||null|阅读量|
|获得蓝钻数量|num|INT||null|获得蓝钻数量|
|耗时|finish_date|INT||null|耗时|
|创建时间|create_date|DATETIME||null|创建时间|
|status|status|VARCHAR||null|是否更正 0：为更正 1：以更正|

##### 查询sql
> select  id,user_id,quiz_time,quiz_id,quiz_result,first_ratio,total,num,finish_date,create_date,status from t_app_user_bl_quiz




#### 表: t_app_user  t_app_user
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|username|username|VARCHAR||null|username|
|password|password|VARCHAR|**必填**|null|password|
|邮箱aes加密值|email|TEXT||null|邮箱aes加密值|
|邮箱sha|email_sha|VARCHAR||null|邮箱sha|
|手机号aes加密值|mobile|TEXT|**必填**|null|手机号aes加密值|
|手机号sha|mobile_sha|VARCHAR|**必填**|null|手机号sha|
|创建人|create_by|VARCHAR||null|创建人|
|更新人|update_by|VARCHAR||null|更新人|
|创建时间|create_date|DATETIME||null|创建时间|
|更新时间|update_date|DATETIME||null|更新时间|
|del_flag|del_flag|TINYINT||null|枚举赋值：*AppUserDelFlag*
0&nbsp;:&nbsp;可用,
1&nbsp;:&nbsp;封禁|

##### 查询sql
> select  id,username,password,email,email_sha,mobile,mobile_sha,create_by,update_by,create_date,update_date,del_flag from t_app_user




#### 表: t_app_parent_course_record  t_app_parent_course_record
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|用户id|user_id|VARCHAR||null|用户id|
|课程id|course_id|VARCHAR||null|课程id|
|音频id|audio_id|VARCHAR||null|音频id|
|learn_statu|learn_statu|INT||null|枚举赋值：*AppParentCourseRecordLearnStatu*
1&nbsp;:&nbsp;未听中,
2&nbsp;:&nbsp;已听完|
|日期|first_listened_date|DATETIME||null|日期|
|存储秒数|last_stop|INT||null|存储秒数|
|create_date|create_date|DATETIME||null|create_date|
|update_date|update_date|DATETIME||null|update_date|

##### 查询sql
> select  id,user_id,course_id,audio_id,learn_statu,first_listened_date,last_stop,create_date,update_date from t_app_parent_course_record




#### 表: t_app_parent_course_favourites  t_app_parent_course_favourites
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|用户id|user_id|VARCHAR||null|用户id|
|课程id|course_id|VARCHAR||null|课程id|
|favourite_statu|favourite_statu|INT||null|枚举赋值：*AppParentCourseFavouritesFavouriteStatu*
1&nbsp;:&nbsp;收藏中,
2&nbsp;:&nbsp;移除收藏|
|首次收藏时间|create_date|DATETIME||null|首次收藏时间|
|update_date|update_date|DATETIME||null|update_date|

##### 查询sql
> select  id,user_id,course_id,favourite_statu,create_date,update_date from t_app_parent_course_favourites




#### 表: t_app_notice_read  t_app_notice_read
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|业务id|task_id|VARCHAR||null|业务id|
|用户id|user_id|VARCHAR||null|用户id|
|发送人|read_time|DATETIME||null|发送人|

##### 查询sql
> select  id,task_id,user_id,read_time from t_app_notice_read




#### 表: t_app_message_read  t_app_message_read
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|主键|id|VARCHAR|**必填**|null|主键|
|业务id|task_id|VARCHAR||null|业务id|
|用户id|user_id|VARCHAR||null|用户id|
|发送人|read_time|DATETIME||null|发送人|

##### 查询sql
> select  id,task_id,user_id,read_time from t_app_message_read




#### 表: sys_user  sys_user
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|id|id|VARCHAR|**必填**|null||
|username|username|VARCHAR|**必填**|null||
|password|password|VARCHAR|**必填**|null||
|age|age|INT||null||
|email|email|VARCHAR||null||
|photo|photo|VARCHAR||null||
|real_name|real_name|VARCHAR||null||
|create_by|create_by|VARCHAR||null||
|update_by|update_by|VARCHAR||null||
|create_date|create_date|DATETIME||null||
|update_date|update_date|DATETIME||null||
|0可用1封禁|del_flag|TINYINT|**必填**|null|0可用1封禁|

##### 查询sql
> select  id,username,password,age,email,photo,real_name,create_by,update_by,create_date,update_date,del_flag from sys_user




#### 表: sys_role_user  sys_role_user
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|user_id|user_id|VARCHAR|**必填**|null||
|role_id|role_id|VARCHAR|**必填**|null||

##### 查询sql
> select  user_id,role_id from sys_role_user




#### 表: sys_role_menu  sys_role_menu
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|role_id|role_id|VARCHAR|**必填**|null||
|menu_id|menu_id|VARCHAR|**必填**|null||

##### 查询sql
> select  role_id,menu_id from sys_role_menu




#### 表: sys_role  sys_role
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|id|id|VARCHAR|**必填**|null||
|role_name|role_name|VARCHAR||null||
|remark|remark|VARCHAR||null||
|create_by|create_by|VARCHAR||null||
|create_date|create_date|DATETIME||null||
|update_by|update_by|VARCHAR||null||
|update_date|update_date|DATETIME||null||

##### 查询sql
> select  id,role_name,remark,create_by,create_date,update_by,update_date from sys_role




#### 表: sys_menu  sys_menu
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|id|id|VARCHAR|**必填**|null||
|name|name|VARCHAR|**必填**|null||
|p_id|p_id|VARCHAR||null||
|url|url|VARCHAR||null||
|排序字段|order_num|INT||null|排序字段|
|图标|icon|VARCHAR||null|图标|
|create_by|create_by|VARCHAR||null||
|create_date|create_date|DATETIME||null||
|update_by|update_by|VARCHAR||null||
|update_date|update_date|DATETIME||null||
|权限|permission|VARCHAR||null|权限|
|1栏目2菜单|menu_type|TINYINT|**必填**|null|1栏目2菜单|

##### 查询sql
> select  id,name,p_id,url,order_num,icon,create_by,create_date,update_by,update_date,permission,menu_type from sys_menu




#### 表: sys_log  sys_log
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|id|id|INT|**必填**|null||
|user_name|user_name|VARCHAR||null||
|ip|ip|VARCHAR||null||
|type|type|VARCHAR||null||
|text|text|VARCHAR||null||
|param|param|TEXT||null||
|create_time|create_time|DATETIME||null||

##### 查询sql
> select  id,user_name,ip,type,text,param,create_time from sys_log




#### 表: sys_job  sys_job
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|id|id|VARCHAR|**必填**|null||
|描述任务|job_name|VARCHAR|**必填**|null|描述任务|
|任务表达式|cron|VARCHAR|**必填**|null|任务表达式|
|status|status|BIT|**必填**|null|状态:0未启动false/1启动true|
|任务执行方法|clazz_path|VARCHAR|**必填**|null|任务执行方法|
|其他描述|job_desc|VARCHAR||null|其他描述|
|create_by|create_by|VARCHAR||null||
|create_date|create_date|DATETIME||null||
|update_by|update_by|VARCHAR||null||
|update_date|update_date|DATETIME||null||

##### 查询sql
> select  id,job_name,cron,status,clazz_path,job_desc,create_by,create_date,update_by,update_date from sys_job




#### 表: sys_dict_type  sys_dict_type
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|id|id|VARCHAR|**必填**|null||
|编码|code|VARCHAR|**必填**|null|编码|
|删除标识|del_flag|CHAR|**必填**|null|删除标识|
|字典名称|text|VARCHAR|**必填**|null|字典名称|
|create_by|create_by|VARCHAR||null||
|create_date|create_date|DATETIME||null||
|update_by|update_by|VARCHAR||null||
|update_date|update_date|DATETIME||null||

##### 查询sql
> select  id,code,del_flag,text,create_by,create_date,update_by,update_date from sys_dict_type




#### 表: sys_dict_item  sys_dict_item
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|id|id|VARCHAR|**必填**|null||
|序号|sequence|INT|**必填**|null|序号|
|值|value|VARCHAR|**必填**|null|值|
|描述|description|VARCHAR|**必填**|null|描述|
|字典id外检|type_id|VARCHAR|**必填**|null|字典id外检|
|create_by|create_by|VARCHAR||null||
|create_date|create_date|DATETIME||null||
|update_by|update_by|VARCHAR||null||
|update_date|update_date|DATETIME||null||
|删除标识|del_flag|CHAR|**必填**|null|删除标识|

##### 查询sql
> select  id,sequence,value,description,type_id,create_by,create_date,update_by,update_date,del_flag from sys_dict_item




#### 表: blog_tag  blog_tag
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|id|id|VARCHAR|**必填**|null||
|标签code|tag_code|VARCHAR|**必填**|null|标签code|
|标签name|tag_name|VARCHAR|**必填**|null|标签name|

##### 查询sql
> select  id,tag_code,tag_name from blog_tag




#### 表: blog_category  blog_category
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|id|id|VARCHAR|**必填**|null||
|sequence|sequence|TINYINT|**必填**|null||
|搜索code|code|VARCHAR|**必填**|null|搜索code|
|类别名称|name|VARCHAR|**必填**|null|类别名称|
|parent_id|parent_id|VARCHAR|**必填**|null|上层id(目前最多两次层)|
|create_by|create_by|VARCHAR||null||
|update_by|update_by|VARCHAR||null||
|create_date|create_date|DATETIME||null||
|update_date|update_date|DATETIME||null||

##### 查询sql
> select  id,sequence,code,name,parent_id,create_by,update_by,create_date,update_date from blog_category




#### 表: blog_article_tag  blog_article_tag
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|article_id|article_id|VARCHAR|**必填**|null||
|tag_id|tag_id|VARCHAR|**必填**|null||

##### 查询sql
> select  article_id,tag_id from blog_article_tag




#### 表: blog_article_category  blog_article_category
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|id|id|VARCHAR|**必填**|null||
|文章id|article_id|VARCHAR|**必填**|null|文章id|
|标签id|category_id|VARCHAR|**必填**|null|标签id|

##### 查询sql
> select  id,article_id,category_id from blog_article_category




#### 表: blog_article  blog_article
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|id|id|VARCHAR|**必填**|null||
|code|code|VARCHAR|**必填**|null|code|
|标题|title|VARCHAR|**必填**|null|标题|
|列表缩略图|first_img|VARCHAR||null|列表缩略图|
|文章内容|content|TEXT|**必填**|null|文章内容|
|阅读次数|read_number|INT|**必填**|null|阅读次数|
|次序(置顶功能)|top_num|INT||null|次序(置顶功能)|
|create_by|create_by|VARCHAR|**必填**|null||
|update_by|update_by|VARCHAR||null||
|create_date|create_date|DATETIME||null||
|update_date|update_date|DATETIME||null||
|0正常1删除|del_flag|TINYINT|**必填**|null|0正常1删除|

##### 查询sql
> select  id,code,title,first_img,content,read_number,top_num,create_by,update_by,create_date,update_date,del_flag from blog_article




#### 表: act_ru_variable  act_ru_variable
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|ID_|ID_|VARCHAR|**必填**|null||
|REV_|REV_|INT||null||
|TYPE_|TYPE_|VARCHAR|**必填**|null||
|NAME_|NAME_|VARCHAR|**必填**|null||
|EXECUTION_ID_|EXECUTION_ID_|VARCHAR||null||
|PROC_INST_ID_|PROC_INST_ID_|VARCHAR||null||
|TASK_ID_|TASK_ID_|VARCHAR||null||
|BYTEARRAY_ID_|BYTEARRAY_ID_|VARCHAR||null||
|DOUBLE_|DOUBLE_|DOUBLE||null||
|LONG_|LONG_|BIGINT||null||
|TEXT_|TEXT_|VARCHAR||null||
|TEXT2_|TEXT2_|VARCHAR||null||

##### 查询sql
> select  ID_,REV_,TYPE_,NAME_,EXECUTION_ID_,PROC_INST_ID_,TASK_ID_,BYTEARRAY_ID_,DOUBLE_,LONG_,TEXT_,TEXT2_ from act_ru_variable




#### 表: act_ru_task  act_ru_task
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|ID_|ID_|VARCHAR|**必填**|null||
|REV_|REV_|INT||null||
|EXECUTION_ID_|EXECUTION_ID_|VARCHAR||null||
|PROC_INST_ID_|PROC_INST_ID_|VARCHAR||null||
|PROC_DEF_ID_|PROC_DEF_ID_|VARCHAR||null||
|NAME_|NAME_|VARCHAR||null||
|PARENT_TASK_ID_|PARENT_TASK_ID_|VARCHAR||null||
|DESCRIPTION_|DESCRIPTION_|VARCHAR||null||
|TASK_DEF_KEY_|TASK_DEF_KEY_|VARCHAR||null||
|OWNER_|OWNER_|VARCHAR||null||
|ASSIGNEE_|ASSIGNEE_|VARCHAR||null||
|DELEGATION_|DELEGATION_|VARCHAR||null||
|PRIORITY_|PRIORITY_|INT||null||
|CREATE_TIME_|CREATE_TIME_|TIMESTAMP||null||
|DUE_DATE_|DUE_DATE_|DATETIME||null||
|CATEGORY_|CATEGORY_|VARCHAR||null||
|SUSPENSION_STATE_|SUSPENSION_STATE_|INT||null||
|TENANT_ID_|TENANT_ID_|VARCHAR||null||
|FORM_KEY_|FORM_KEY_|VARCHAR||null||

##### 查询sql
> select  ID_,REV_,EXECUTION_ID_,PROC_INST_ID_,PROC_DEF_ID_,NAME_,PARENT_TASK_ID_,DESCRIPTION_,TASK_DEF_KEY_,OWNER_,ASSIGNEE_,DELEGATION_,PRIORITY_,CREATE_TIME_,DUE_DATE_,CATEGORY_,SUSPENSION_STATE_,TENANT_ID_,FORM_KEY_ from act_ru_task




#### 表: act_ru_job  act_ru_job
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|ID_|ID_|VARCHAR|**必填**|null||
|REV_|REV_|INT||null||
|TYPE_|TYPE_|VARCHAR|**必填**|null||
|LOCK_EXP_TIME_|LOCK_EXP_TIME_|TIMESTAMP||null||
|LOCK_OWNER_|LOCK_OWNER_|VARCHAR||null||
|EXCLUSIVE_|EXCLUSIVE_|BIT||null||
|EXECUTION_ID_|EXECUTION_ID_|VARCHAR||null||
|PROCESS_INSTANCE_ID_|PROCESS_INSTANCE_ID_|VARCHAR||null||
|PROC_DEF_ID_|PROC_DEF_ID_|VARCHAR||null||
|RETRIES_|RETRIES_|INT||null||
|EXCEPTION_STACK_ID_|EXCEPTION_STACK_ID_|VARCHAR||null||
|EXCEPTION_MSG_|EXCEPTION_MSG_|VARCHAR||null||
|DUEDATE_|DUEDATE_|TIMESTAMP||null||
|REPEAT_|REPEAT_|VARCHAR||null||
|HANDLER_TYPE_|HANDLER_TYPE_|VARCHAR||null||
|HANDLER_CFG_|HANDLER_CFG_|VARCHAR||null||
|TENANT_ID_|TENANT_ID_|VARCHAR||null||

##### 查询sql
> select  ID_,REV_,TYPE_,LOCK_EXP_TIME_,LOCK_OWNER_,EXCLUSIVE_,EXECUTION_ID_,PROCESS_INSTANCE_ID_,PROC_DEF_ID_,RETRIES_,EXCEPTION_STACK_ID_,EXCEPTION_MSG_,DUEDATE_,REPEAT_,HANDLER_TYPE_,HANDLER_CFG_,TENANT_ID_ from act_ru_job




#### 表: act_ru_identitylink  act_ru_identitylink
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|ID_|ID_|VARCHAR|**必填**|null||
|REV_|REV_|INT||null||
|GROUP_ID_|GROUP_ID_|VARCHAR||null||
|TYPE_|TYPE_|VARCHAR||null||
|USER_ID_|USER_ID_|VARCHAR||null||
|TASK_ID_|TASK_ID_|VARCHAR||null||
|PROC_INST_ID_|PROC_INST_ID_|VARCHAR||null||
|PROC_DEF_ID_|PROC_DEF_ID_|VARCHAR||null||

##### 查询sql
> select  ID_,REV_,GROUP_ID_,TYPE_,USER_ID_,TASK_ID_,PROC_INST_ID_,PROC_DEF_ID_ from act_ru_identitylink




#### 表: act_ru_execution  act_ru_execution
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|ID_|ID_|VARCHAR|**必填**|null||
|REV_|REV_|INT||null||
|PROC_INST_ID_|PROC_INST_ID_|VARCHAR||null||
|BUSINESS_KEY_|BUSINESS_KEY_|VARCHAR||null||
|PARENT_ID_|PARENT_ID_|VARCHAR||null||
|PROC_DEF_ID_|PROC_DEF_ID_|VARCHAR||null||
|SUPER_EXEC_|SUPER_EXEC_|VARCHAR||null||
|ACT_ID_|ACT_ID_|VARCHAR||null||
|IS_ACTIVE_|IS_ACTIVE_|TINYINT||null||
|IS_CONCURRENT_|IS_CONCURRENT_|TINYINT||null||
|IS_SCOPE_|IS_SCOPE_|TINYINT||null||
|IS_EVENT_SCOPE_|IS_EVENT_SCOPE_|TINYINT||null||
|SUSPENSION_STATE_|SUSPENSION_STATE_|INT||null||
|CACHED_ENT_STATE_|CACHED_ENT_STATE_|INT||null||
|TENANT_ID_|TENANT_ID_|VARCHAR||null||
|NAME_|NAME_|VARCHAR||null||
|LOCK_TIME_|LOCK_TIME_|TIMESTAMP||null||

##### 查询sql
> select  ID_,REV_,PROC_INST_ID_,BUSINESS_KEY_,PARENT_ID_,PROC_DEF_ID_,SUPER_EXEC_,ACT_ID_,IS_ACTIVE_,IS_CONCURRENT_,IS_SCOPE_,IS_EVENT_SCOPE_,SUSPENSION_STATE_,CACHED_ENT_STATE_,TENANT_ID_,NAME_,LOCK_TIME_ from act_ru_execution




#### 表: act_ru_event_subscr  act_ru_event_subscr
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|ID_|ID_|VARCHAR|**必填**|null||
|REV_|REV_|INT||null||
|EVENT_TYPE_|EVENT_TYPE_|VARCHAR|**必填**|null||
|EVENT_NAME_|EVENT_NAME_|VARCHAR||null||
|EXECUTION_ID_|EXECUTION_ID_|VARCHAR||null||
|PROC_INST_ID_|PROC_INST_ID_|VARCHAR||null||
|ACTIVITY_ID_|ACTIVITY_ID_|VARCHAR||null||
|CONFIGURATION_|CONFIGURATION_|VARCHAR||null||
|CREATED_|CREATED_|TIMESTAMP|**必填**|null||
|PROC_DEF_ID_|PROC_DEF_ID_|VARCHAR||null||
|TENANT_ID_|TENANT_ID_|VARCHAR||null||

##### 查询sql
> select  ID_,REV_,EVENT_TYPE_,EVENT_NAME_,EXECUTION_ID_,PROC_INST_ID_,ACTIVITY_ID_,CONFIGURATION_,CREATED_,PROC_DEF_ID_,TENANT_ID_ from act_ru_event_subscr




#### 表: act_re_procdef  act_re_procdef
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|ID_|ID_|VARCHAR|**必填**|null||
|REV_|REV_|INT||null||
|CATEGORY_|CATEGORY_|VARCHAR||null||
|NAME_|NAME_|VARCHAR||null||
|KEY_|KEY_|VARCHAR|**必填**|null||
|VERSION_|VERSION_|INT|**必填**|null||
|DEPLOYMENT_ID_|DEPLOYMENT_ID_|VARCHAR||null||
|RESOURCE_NAME_|RESOURCE_NAME_|VARCHAR||null||
|DGRM_RESOURCE_NAME_|DGRM_RESOURCE_NAME_|VARCHAR||null||
|DESCRIPTION_|DESCRIPTION_|VARCHAR||null||
|HAS_START_FORM_KEY_|HAS_START_FORM_KEY_|TINYINT||null||
|HAS_GRAPHICAL_NOTATION_|HAS_GRAPHICAL_NOTATION_|TINYINT||null||
|SUSPENSION_STATE_|SUSPENSION_STATE_|INT||null||
|TENANT_ID_|TENANT_ID_|VARCHAR||null||

##### 查询sql
> select  ID_,REV_,CATEGORY_,NAME_,KEY_,VERSION_,DEPLOYMENT_ID_,RESOURCE_NAME_,DGRM_RESOURCE_NAME_,DESCRIPTION_,HAS_START_FORM_KEY_,HAS_GRAPHICAL_NOTATION_,SUSPENSION_STATE_,TENANT_ID_ from act_re_procdef




#### 表: act_re_model  act_re_model
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|ID_|ID_|VARCHAR|**必填**|null||
|REV_|REV_|INT||null||
|NAME_|NAME_|VARCHAR||null||
|KEY_|KEY_|VARCHAR||null||
|CATEGORY_|CATEGORY_|VARCHAR||null||
|CREATE_TIME_|CREATE_TIME_|TIMESTAMP||null||
|LAST_UPDATE_TIME_|LAST_UPDATE_TIME_|TIMESTAMP||null||
|VERSION_|VERSION_|INT||null||
|META_INFO_|META_INFO_|VARCHAR||null||
|DEPLOYMENT_ID_|DEPLOYMENT_ID_|VARCHAR||null||
|EDITOR_SOURCE_VALUE_ID_|EDITOR_SOURCE_VALUE_ID_|VARCHAR||null||
|EDITOR_SOURCE_EXTRA_VALUE_ID_|EDITOR_SOURCE_EXTRA_VALUE_ID_|VARCHAR||null||
|TENANT_ID_|TENANT_ID_|VARCHAR||null||

##### 查询sql
> select  ID_,REV_,NAME_,KEY_,CATEGORY_,CREATE_TIME_,LAST_UPDATE_TIME_,VERSION_,META_INFO_,DEPLOYMENT_ID_,EDITOR_SOURCE_VALUE_ID_,EDITOR_SOURCE_EXTRA_VALUE_ID_,TENANT_ID_ from act_re_model




#### 表: act_re_deployment  act_re_deployment
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|ID_|ID_|VARCHAR|**必填**|null||
|NAME_|NAME_|VARCHAR||null||
|CATEGORY_|CATEGORY_|VARCHAR||null||
|TENANT_ID_|TENANT_ID_|VARCHAR||null||
|DEPLOY_TIME_|DEPLOY_TIME_|TIMESTAMP||null||

##### 查询sql
> select  ID_,NAME_,CATEGORY_,TENANT_ID_,DEPLOY_TIME_ from act_re_deployment




#### 表: act_procdef_info  act_procdef_info
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|ID_|ID_|VARCHAR|**必填**|null||
|PROC_DEF_ID_|PROC_DEF_ID_|VARCHAR|**必填**|null||
|REV_|REV_|INT||null||
|INFO_JSON_ID_|INFO_JSON_ID_|VARCHAR||null||

##### 查询sql
> select  ID_,PROC_DEF_ID_,REV_,INFO_JSON_ID_ from act_procdef_info




#### 表: act_id_user  act_id_user
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|ID_|ID_|VARCHAR|**必填**|null||
|REV_|REV_|INT||null||
|FIRST_|FIRST_|VARCHAR||null||
|LAST_|LAST_|VARCHAR||null||
|EMAIL_|EMAIL_|VARCHAR||null||
|PWD_|PWD_|VARCHAR||null||
|PICTURE_ID_|PICTURE_ID_|VARCHAR||null||

##### 查询sql
> select  ID_,REV_,FIRST_,LAST_,EMAIL_,PWD_,PICTURE_ID_ from act_id_user




#### 表: act_id_membership  act_id_membership
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|USER_ID_|USER_ID_|VARCHAR|**必填**|null||
|GROUP_ID_|GROUP_ID_|VARCHAR|**必填**|null||

##### 查询sql
> select  USER_ID_,GROUP_ID_ from act_id_membership




#### 表: act_id_info  act_id_info
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|ID_|ID_|VARCHAR|**必填**|null||
|REV_|REV_|INT||null||
|USER_ID_|USER_ID_|VARCHAR||null||
|TYPE_|TYPE_|VARCHAR||null||
|KEY_|KEY_|VARCHAR||null||
|VALUE_|VALUE_|VARCHAR||null||
|PASSWORD_|PASSWORD_|LONGBLOB||null||
|PARENT_ID_|PARENT_ID_|VARCHAR||null||

##### 查询sql
> select  ID_,REV_,USER_ID_,TYPE_,KEY_,VALUE_,PASSWORD_,PARENT_ID_ from act_id_info




#### 表: act_id_group  act_id_group
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|ID_|ID_|VARCHAR|**必填**|null||
|REV_|REV_|INT||null||
|NAME_|NAME_|VARCHAR||null||
|TYPE_|TYPE_|VARCHAR||null||

##### 查询sql
> select  ID_,REV_,NAME_,TYPE_ from act_id_group




#### 表: act_hi_varinst  act_hi_varinst
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|ID_|ID_|VARCHAR|**必填**|null||
|PROC_INST_ID_|PROC_INST_ID_|VARCHAR||null||
|EXECUTION_ID_|EXECUTION_ID_|VARCHAR||null||
|TASK_ID_|TASK_ID_|VARCHAR||null||
|NAME_|NAME_|VARCHAR|**必填**|null||
|VAR_TYPE_|VAR_TYPE_|VARCHAR||null||
|REV_|REV_|INT||null||
|BYTEARRAY_ID_|BYTEARRAY_ID_|VARCHAR||null||
|DOUBLE_|DOUBLE_|DOUBLE||null||
|LONG_|LONG_|BIGINT||null||
|TEXT_|TEXT_|VARCHAR||null||
|TEXT2_|TEXT2_|VARCHAR||null||
|CREATE_TIME_|CREATE_TIME_|DATETIME||null||
|LAST_UPDATED_TIME_|LAST_UPDATED_TIME_|DATETIME||null||

##### 查询sql
> select  ID_,PROC_INST_ID_,EXECUTION_ID_,TASK_ID_,NAME_,VAR_TYPE_,REV_,BYTEARRAY_ID_,DOUBLE_,LONG_,TEXT_,TEXT2_,CREATE_TIME_,LAST_UPDATED_TIME_ from act_hi_varinst




#### 表: act_hi_taskinst  act_hi_taskinst
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|ID_|ID_|VARCHAR|**必填**|null||
|PROC_DEF_ID_|PROC_DEF_ID_|VARCHAR||null||
|TASK_DEF_KEY_|TASK_DEF_KEY_|VARCHAR||null||
|PROC_INST_ID_|PROC_INST_ID_|VARCHAR||null||
|EXECUTION_ID_|EXECUTION_ID_|VARCHAR||null||
|NAME_|NAME_|VARCHAR||null||
|PARENT_TASK_ID_|PARENT_TASK_ID_|VARCHAR||null||
|DESCRIPTION_|DESCRIPTION_|VARCHAR||null||
|OWNER_|OWNER_|VARCHAR||null||
|ASSIGNEE_|ASSIGNEE_|VARCHAR||null||
|START_TIME_|START_TIME_|DATETIME|**必填**|null||
|CLAIM_TIME_|CLAIM_TIME_|DATETIME||null||
|END_TIME_|END_TIME_|DATETIME||null||
|DURATION_|DURATION_|BIGINT||null||
|DELETE_REASON_|DELETE_REASON_|VARCHAR||null||
|PRIORITY_|PRIORITY_|INT||null||
|DUE_DATE_|DUE_DATE_|DATETIME||null||
|FORM_KEY_|FORM_KEY_|VARCHAR||null||
|CATEGORY_|CATEGORY_|VARCHAR||null||
|TENANT_ID_|TENANT_ID_|VARCHAR||null||

##### 查询sql
> select  ID_,PROC_DEF_ID_,TASK_DEF_KEY_,PROC_INST_ID_,EXECUTION_ID_,NAME_,PARENT_TASK_ID_,DESCRIPTION_,OWNER_,ASSIGNEE_,START_TIME_,CLAIM_TIME_,END_TIME_,DURATION_,DELETE_REASON_,PRIORITY_,DUE_DATE_,FORM_KEY_,CATEGORY_,TENANT_ID_ from act_hi_taskinst




#### 表: act_hi_procinst  act_hi_procinst
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|ID_|ID_|VARCHAR|**必填**|null||
|PROC_INST_ID_|PROC_INST_ID_|VARCHAR|**必填**|null||
|BUSINESS_KEY_|BUSINESS_KEY_|VARCHAR||null||
|PROC_DEF_ID_|PROC_DEF_ID_|VARCHAR|**必填**|null||
|START_TIME_|START_TIME_|DATETIME|**必填**|null||
|END_TIME_|END_TIME_|DATETIME||null||
|DURATION_|DURATION_|BIGINT||null||
|START_USER_ID_|START_USER_ID_|VARCHAR||null||
|START_ACT_ID_|START_ACT_ID_|VARCHAR||null||
|END_ACT_ID_|END_ACT_ID_|VARCHAR||null||
|SUPER_PROCESS_INSTANCE_ID_|SUPER_PROCESS_INSTANCE_ID_|VARCHAR||null||
|DELETE_REASON_|DELETE_REASON_|VARCHAR||null||
|TENANT_ID_|TENANT_ID_|VARCHAR||null||
|NAME_|NAME_|VARCHAR||null||

##### 查询sql
> select  ID_,PROC_INST_ID_,BUSINESS_KEY_,PROC_DEF_ID_,START_TIME_,END_TIME_,DURATION_,START_USER_ID_,START_ACT_ID_,END_ACT_ID_,SUPER_PROCESS_INSTANCE_ID_,DELETE_REASON_,TENANT_ID_,NAME_ from act_hi_procinst




#### 表: act_hi_identitylink  act_hi_identitylink
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|ID_|ID_|VARCHAR|**必填**|null||
|GROUP_ID_|GROUP_ID_|VARCHAR||null||
|TYPE_|TYPE_|VARCHAR||null||
|USER_ID_|USER_ID_|VARCHAR||null||
|TASK_ID_|TASK_ID_|VARCHAR||null||
|PROC_INST_ID_|PROC_INST_ID_|VARCHAR||null||

##### 查询sql
> select  ID_,GROUP_ID_,TYPE_,USER_ID_,TASK_ID_,PROC_INST_ID_ from act_hi_identitylink




#### 表: act_hi_detail  act_hi_detail
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|ID_|ID_|VARCHAR|**必填**|null||
|TYPE_|TYPE_|VARCHAR|**必填**|null||
|PROC_INST_ID_|PROC_INST_ID_|VARCHAR||null||
|EXECUTION_ID_|EXECUTION_ID_|VARCHAR||null||
|TASK_ID_|TASK_ID_|VARCHAR||null||
|ACT_INST_ID_|ACT_INST_ID_|VARCHAR||null||
|NAME_|NAME_|VARCHAR|**必填**|null||
|VAR_TYPE_|VAR_TYPE_|VARCHAR||null||
|REV_|REV_|INT||null||
|TIME_|TIME_|DATETIME|**必填**|null||
|BYTEARRAY_ID_|BYTEARRAY_ID_|VARCHAR||null||
|DOUBLE_|DOUBLE_|DOUBLE||null||
|LONG_|LONG_|BIGINT||null||
|TEXT_|TEXT_|VARCHAR||null||
|TEXT2_|TEXT2_|VARCHAR||null||

##### 查询sql
> select  ID_,TYPE_,PROC_INST_ID_,EXECUTION_ID_,TASK_ID_,ACT_INST_ID_,NAME_,VAR_TYPE_,REV_,TIME_,BYTEARRAY_ID_,DOUBLE_,LONG_,TEXT_,TEXT2_ from act_hi_detail




#### 表: act_hi_comment  act_hi_comment
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|ID_|ID_|VARCHAR|**必填**|null||
|TYPE_|TYPE_|VARCHAR||null||
|TIME_|TIME_|DATETIME|**必填**|null||
|USER_ID_|USER_ID_|VARCHAR||null||
|TASK_ID_|TASK_ID_|VARCHAR||null||
|PROC_INST_ID_|PROC_INST_ID_|VARCHAR||null||
|ACTION_|ACTION_|VARCHAR||null||
|MESSAGE_|MESSAGE_|VARCHAR||null||
|FULL_MSG_|FULL_MSG_|LONGBLOB||null||

##### 查询sql
> select  ID_,TYPE_,TIME_,USER_ID_,TASK_ID_,PROC_INST_ID_,ACTION_,MESSAGE_,FULL_MSG_ from act_hi_comment




#### 表: act_hi_attachment  act_hi_attachment
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|ID_|ID_|VARCHAR|**必填**|null||
|REV_|REV_|INT||null||
|USER_ID_|USER_ID_|VARCHAR||null||
|NAME_|NAME_|VARCHAR||null||
|DESCRIPTION_|DESCRIPTION_|VARCHAR||null||
|TYPE_|TYPE_|VARCHAR||null||
|TASK_ID_|TASK_ID_|VARCHAR||null||
|PROC_INST_ID_|PROC_INST_ID_|VARCHAR||null||
|URL_|URL_|VARCHAR||null||
|CONTENT_ID_|CONTENT_ID_|VARCHAR||null||
|TIME_|TIME_|DATETIME||null||

##### 查询sql
> select  ID_,REV_,USER_ID_,NAME_,DESCRIPTION_,TYPE_,TASK_ID_,PROC_INST_ID_,URL_,CONTENT_ID_,TIME_ from act_hi_attachment




#### 表: act_hi_actinst  act_hi_actinst
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|ID_|ID_|VARCHAR|**必填**|null||
|PROC_DEF_ID_|PROC_DEF_ID_|VARCHAR|**必填**|null||
|PROC_INST_ID_|PROC_INST_ID_|VARCHAR|**必填**|null||
|EXECUTION_ID_|EXECUTION_ID_|VARCHAR|**必填**|null||
|ACT_ID_|ACT_ID_|VARCHAR|**必填**|null||
|TASK_ID_|TASK_ID_|VARCHAR||null||
|CALL_PROC_INST_ID_|CALL_PROC_INST_ID_|VARCHAR||null||
|ACT_NAME_|ACT_NAME_|VARCHAR||null||
|ACT_TYPE_|ACT_TYPE_|VARCHAR|**必填**|null||
|ASSIGNEE_|ASSIGNEE_|VARCHAR||null||
|START_TIME_|START_TIME_|DATETIME|**必填**|null||
|END_TIME_|END_TIME_|DATETIME||null||
|DURATION_|DURATION_|BIGINT||null||
|TENANT_ID_|TENANT_ID_|VARCHAR||null||

##### 查询sql
> select  ID_,PROC_DEF_ID_,PROC_INST_ID_,EXECUTION_ID_,ACT_ID_,TASK_ID_,CALL_PROC_INST_ID_,ACT_NAME_,ACT_TYPE_,ASSIGNEE_,START_TIME_,END_TIME_,DURATION_,TENANT_ID_ from act_hi_actinst




#### 表: act_ge_property  act_ge_property
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|NAME_|NAME_|VARCHAR|**必填**|null||
|VALUE_|VALUE_|VARCHAR||null||
|REV_|REV_|INT||null||

##### 查询sql
> select  NAME_,VALUE_,REV_ from act_ge_property




#### 表: act_ge_bytearray  act_ge_bytearray
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|ID_|ID_|VARCHAR|**必填**|null||
|REV_|REV_|INT||null||
|NAME_|NAME_|VARCHAR||null||
|DEPLOYMENT_ID_|DEPLOYMENT_ID_|VARCHAR||null||
|BYTES_|BYTES_|LONGBLOB||null||
|GENERATED_|GENERATED_|TINYINT||null||

##### 查询sql
> select  ID_,REV_,NAME_,DEPLOYMENT_ID_,BYTES_,GENERATED_ from act_ge_bytearray




#### 表: act_evt_log  act_evt_log
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|LOG_NR_|LOG_NR_|BIGINT|**必填**|null||
|TYPE_|TYPE_|VARCHAR||null||
|PROC_DEF_ID_|PROC_DEF_ID_|VARCHAR||null||
|PROC_INST_ID_|PROC_INST_ID_|VARCHAR||null||
|EXECUTION_ID_|EXECUTION_ID_|VARCHAR||null||
|TASK_ID_|TASK_ID_|VARCHAR||null||
|TIME_STAMP_|TIME_STAMP_|TIMESTAMP|**必填**|null||
|USER_ID_|USER_ID_|VARCHAR||null||
|DATA_|DATA_|LONGBLOB||null||
|LOCK_OWNER_|LOCK_OWNER_|VARCHAR||null||
|LOCK_TIME_|LOCK_TIME_|TIMESTAMP||null||
|IS_PROCESSED_|IS_PROCESSED_|TINYINT||null||

##### 查询sql
> select  LOG_NR_,TYPE_,PROC_DEF_ID_,PROC_INST_ID_,EXECUTION_ID_,TASK_ID_,TIME_STAMP_,USER_ID_,DATA_,LOCK_OWNER_,LOCK_TIME_,IS_PROCESSED_ from act_evt_log




#### 表: act_assignee  act_assignee
##### 数据说明
| Name           | Code   | Data Type   | Mandatory | Default | Comment |
|----------------|--------|--------------|--------|--------|---------|
|id|id|VARCHAR|**必填**|null||
|节点id|sid|VARCHAR|**必填**|null|节点id|
|办理人|assignee|VARCHAR||null|办理人|
|候选组(角色)|role_id|VARCHAR||null|候选组(角色)|
|assignee_type|assignee_type|INT|**必填**|null|办理人类型1办理人2候选人3组|
|节点名称|activti_name|VARCHAR||null|节点名称|

##### 查询sql
> select  id,sid,assignee,role_id,assignee_type,activti_name from act_assignee




