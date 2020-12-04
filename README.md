

此代码可以运行起来，但是需要修改HdfsConn.java中的defaultFS和HbaseConn中的quorum和rootdir。

修改完后

执行CreateTable建表

然后即可运行程序


目前程序可以运行起来，但是并不能在hadoop中添加目录或上传文件，还有些问题没有调通，具体问题出现在

HdfsConn 中的 fileSystem = FileSystem.get(uri,configuration,"root"); 处，在这里无法正常获得FileSystem，暂不知什么原因（这一句在其它地方运行正常）。


