Cosmos框架1.0.4版本即将放至Google Code，其中增加了对JSON的支持，并成功地与ExtJS框架进行了整合。

# Introduction #
Cosmos框架1.0.4成功整合JSON，使得Cosmos框架又有了进步，能够在使用Ajax的环境中，丢弃所有的JSP代码的开发，从而将j2ee程序的开发流程成功地降低为：开发存储过程，开发HTML页面，开发JS脚本这些步骤，使得虽然是在利用Java开发应用，但却不需要写任何Java代码，降低了J2EE程序的开发门槛。

# Details #

> 经过两天的努力，成功的将Cosmos框架和ExtJS的JavaScript框架整合起来，并简单做了一个GridPanel的样例，附件是截图和所有需要的代码。

> 经过统计，包括所有IDE生成的代码，一共是103行代码，其中服务器端代码只包含建立存储过程的SQL语句，为14行代码，其中存储过程体只有5行代码。

> 客户端代码包括HTML文件和JS文件总共89行，JS为调用ExtJS框架的所有代码。

> 服务器端和客户端利用Cosmos框架自身的JSON封装，将存储过程的结果集和输出参数封装成JSON数据交与JavaScript使用。客户端利用ExtJS的GridPanel类进行表格显示。

> 从上面分析可以看出，Cosmos框架确实减少了服务器端代码量，而ExtJS则减少了浏览器端开发的代码量。