
mybatis的核心流程：
1：解析流程，
 1.1: 对Configuration.xml文件进行解析，environment标签封装成数据源(有数据库连接的对象)，将所有的xml封装成一个Configuration对象
 1.2：根据mapper标签获取mapper文件，对mapper文件进行解析，获取其namespace。
 1.3：解析mappers中的sql，resultMap，进行保存
 1.4：解析CURD(select|update|read|delete)标签,一个CURD标签封装成一个mappedstatment，放在Configuration的List集合里，
 key=namespace(mapper的)+id(CURD标签的),每一个mappedstatement都包括了parametertype，resultmap，resulttype，SqlSource，
 sql语句处理：对每个sql脚本或动态标签封装成相应的sqlNode，并组装成一个MixedSqlNode,然后将这个MixedSqlNode封装到SqlSource中，当SqlSource调用
 getBoundSql方法时，会对mixedSqlNode进行解析，调用每个SqlNode的apply()方法，这时${}这样的会替换成值，并把#{username}解析成“?”，把username参数
 添加到list集合里，这样预编译后的可执行的sql和list集合就形成了BoundSql对象，BoundSql封装在SqlSource里，SqlSource封装在mappedStatement里
   1.4.1:SqlNode mapper的xml文件进行解析时，会把每一个sql脚本或动态标签（if,where..）封装成SqlNode()，每个SqlNode的apply()方法就是解析方法;
     IfSqlNode           对应if标签
     StaticTextSqlNode   纯文本或者包含#{}的sql语句
     TextSqlNode         sql语句只要包含${}，他就是被TextSqlNode节点  
     WhereSqlNode        对应where标签
     ForeachSqlNode      对应Foreach标签
     TrimSqlNode         对应Trim标签
     MixedSqlNode        组合节点，所有的SqlNode最终都组装到这里，在获取sql里的时候，也是对该MixedSqlNode进行解析
   1.4.2:SqlSource
     DynamicSqlSource：存储的是整个statement标签（select、inset等CRUD标签）中的SQL信息（带${}SQL文本/动态标签【if\where\foreach】）
   	 RawSqlSource：存储的是整个statement标签（select、inset等CRUD标签）中的SQL信息（只带#{}或者是#{}，${}都不带的）
     StaticSqlSource：存储被解析之后的SQL信息和参数信息，
   1.4.3:BoundSql: 封装有可执行的sql语句，当sqlSource中有#{username}时，Boundsql还封装有username的list集合。
   
 2：执行流程
  2.1：获取SqlSession对象，
  2.2：调用相应方法，输入参数“statementId”和"param"
  2.2: 通过statementId从Configuration的list列表列表中获取MappedStatment对象，
  2.3：通过mappedStatment获取sql执行脚本，然后由Executor来进行执行
    Executor包含四个部分：
             ParameterHandler:进行参数，java对象和jdbc对象的处理
             ResultSetHandler：在进行查询后，利用反射将查询的结果集封装成pojo对象
             executor:执行sql语句
             statementHandler:创建相应的statment，如PrepareStatement，CallableStatement
  2.4将结果返回给用户
   