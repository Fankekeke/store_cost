### 基于SpringBoot + Vue的超市成本管理系统

超市管理，超市商品管理 商品进销存 超市订单管理

##### 权限安全与系统定制
###### 系统登录与密码修改： 建立安全的身份验证机制，支持个性化系统参数定制，确保管理权限的私密性与系统环境的适配性。

##### 数据驾驶舱与经营分析
###### 出入库数据统计： 实时汇总近七天的订单及流转信息，通过动态数据直观呈现超市日常运营效率与物资周转动态。

###### 订单与销售统计： 深度分析订单类型分布与商品售出占比，利用数据可视化手段辅助管理层精准掌握市场消费偏好。

##### 供应链与交易闭环管理
###### 供应商准入管理： 严格审核供货商资质，建立合作档案与信用评价体系，从源头保障超市商品的品质安全与供应稳定。

###### 订单管理与执行： 全生命周期追踪商品采购与销售订单，确保每一笔交易流程合规，实现零售业务的数字化闭环。

##### 仓储物流与单据追溯
###### 出入库记录及导出： 详细记录物资进出详情，支持一键导出标准格式的单据，为财务对账与库存审计提供法定依据。

###### 库房管理与盘库： 动态监控货位状态，定期执行自动化盘点统计，实时核纠库存差异，确保存货资产账实相符。

##### 人力资源与薪资激励
###### 员工管理与信息修改： 集中维护员工数字化档案，实时更新职级与联系方式，构建灵活的人事基础数据管理体系。

###### 薪资涨幅与发放记录： 统计员工薪酬变动趋势并归档发放凭证，通过透明的财务记录提升员工满意度与内部管理效率。

#### 安装环境

JAVA 环境 

Node.js环境 [https://nodejs.org/en/] 选择14.17

Yarn 打开cmd， 输入npm install -g yarn !!!必须安装完毕nodejs

Mysql 数据库 [https://blog.csdn.net/qq_40303031/article/details/88935262] 一定要把账户和密码记住

redis

Idea 编译器 [https://blog.csdn.net/weixin_44505194/article/details/104452880]

WebStorm OR VScode 编译器 [https://www.jianshu.com/p/d63b5bae9dff]

#### 采用技术及功能

后端：SpringBoot、MybatisPlus、MySQL、Redis、
前端：Vue、Apex、Antd、Axios

管理员：系统登录，修改密码，系统定制，数据统计（七天内订单，入库，出库信息统计），订单类型统计，售出统计，供应商准入管理，订单管理，出库记录（查看出库详情，导出出库单），入库记录（查看入库详情，导出入库单）

员工管理（修改员工详细信息），员工薪资涨幅（员工涨幅统计），员工薪资发放记录，库房管理，盘库统计，物品出入库详情

#### 前台启动方式
安装所需文件 yarn install 
运行 yarn run dev

#### 后端启动方式

1.首先启动redis，进入redis目录终端。输入redis-server回车
2.导入sql文件，修改数据库与redis连接配置
3.idea中启动后端项目

#### 默认后台账户密码
[管理员]
system
1234qwer


#### 项目截图

|  |  |
|---------------------|---------------------|
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/b0ee63524e1473578f7f1c49e9ed534.jpg) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/3ad8a82ffa2d8bfd1293d5772b554c8.jpg) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/a6becf78bc5b5c47be476e73aab4229.jpg) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1e9d5f6a7142421fe5c09fa03d37ab2.jpg) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/60205b77c69c7ea0d9d4cb5e18e33d1.jpg) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/eb815c82c3b6b8b50c54d6f9288aabc.jpg) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/04083fe7f139860f7b8428b7f171756.jpg) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/bd14cfb08f1f2e0cac8f03b85a100af.jpg) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/7ba91cf08f3ddd2018424719dd9dbf8.jpg) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/b3819f127084af2444feeb1d283c816.jpg) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/work/936e9baf53eb9a217af4f89c616dc19.png) |

#### 演示视频

[项目视频：基于SpringBoot的超市成本管理系统](https://www.bilibili.com/video/BV1Kg411a7jT/)

#### 获取方式

Email: fan1ke2ke@gmail.com

WeChat: `Storm_Berserker`

`附带部署与讲解服务，因为要恰饭资源非免费，伸手党勿扰，谢谢理解😭`

> 1.项目纯原创，不做二手贩子 2.一次购买终身有效 3.项目讲解持续到答辩结束 4.非常负责的答辩指导 5.**黑奴价格**

> 项目部署调试不好包退！功能逻辑没讲明白包退！

#### 其它资源

[2025年-答辩顺利通过-客户评价🍜](https://berserker287.github.io/2025/06/18/2025%E5%B9%B4%E7%AD%94%E8%BE%A9%E9%A1%BA%E5%88%A9%E9%80%9A%E8%BF%87/)

[2024年-答辩顺利通过-客户评价👻](https://berserker287.github.io/2024/06/06/2024%E5%B9%B4%E7%AD%94%E8%BE%A9%E9%A1%BA%E5%88%A9%E9%80%9A%E8%BF%87/)

[2023年-答辩顺利通过-客户评价🐢](https://berserker287.github.io/2023/06/14/2023%E5%B9%B4%E7%AD%94%E8%BE%A9%E9%A1%BA%E5%88%A9%E9%80%9A%E8%BF%87/)

[2022年-答辩通过率100%-客户评价🐣](https://berserker287.github.io/2022/05/25/%E9%A1%B9%E7%9B%AE%E4%BA%A4%E6%98%93%E8%AE%B0%E5%BD%95/)

[毕业答辩导师提问的高频问题](https://berserker287.github.io/2023/06/13/%E6%AF%95%E4%B8%9A%E7%AD%94%E8%BE%A9%E5%AF%BC%E5%B8%88%E6%8F%90%E9%97%AE%E7%9A%84%E9%AB%98%E9%A2%91%E9%97%AE%E9%A2%98/)

[50个高频答辩问题-技术篇](https://berserker287.github.io/2023/06/13/50%E4%B8%AA%E9%AB%98%E9%A2%91%E7%AD%94%E8%BE%A9%E9%97%AE%E9%A2%98-%E6%8A%80%E6%9C%AF%E7%AF%87/)

[计算机毕设答辩时都会问到哪些问题？](https://www.zhihu.com/question/31020988)

[计算机专业毕业答辩小tips](https://zhuanlan.zhihu.com/p/145911029)


#### 接JAVAWEB毕设，纯原创，价格公道，诚信第一

`网站建设、小程序、H5、APP、各种系统 选题+开题报告+任务书+程序定制+安装调试+项目讲解+论文+答辩PPT`

More info: [悲伤的橘子树](https://berserker287.github.io/)

<p><img align="center" src="https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/%E5%90%88%E4%BD%9C%E7%89%A9%E6%96%99%E6%A0%B7%E5%BC%8F%20(3).png" alt="fankekeke" /></p>
