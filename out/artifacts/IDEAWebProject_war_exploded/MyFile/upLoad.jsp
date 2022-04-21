
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件的上传与下载</title>
    <link rel="stylesheet" type="text/css" href="../JavaScript/bootstrap-3.4.1/dist/css/bootstrap.css"/>
    <script src="../JavaScript/jquery-1.12.4.js"></script>
    <script src="../JavaScript/bootstrap-3.4.1/dist/js/bootstrap.min.js"></script>

</head>
<body>
<h2 class="col-xs-offset-4">文件上传</h2>

<form class="form-horizontal" action="FileServlet?op=doUpLoad" method="post" enctype="multipart/form-data">

    <div class="form-group">
        <label for="fileName" class="col-sm-2 control-label">文件名</label>
        <div class="col-sm-5">
            <input type="text" class="form-control" id="fileName" name="fileName" placeholder="FileName">
        </div>
    </div>
    <div class="form-group">
        <label for="myFile" class="col-sm-2 control-label">上传附件</label>
        <div class="col-sm-5">
            <input type="file" id="myFile" name="myFile" placeholder="File">
        </div>
    </div><div class="form-group">
        <label for="fileDesc" class="col-sm-2 control-label">文件说明</label>
        <div class="col-sm-5">
            <textarea class="form-control" rows="3" id="fileDesc" name="fileDesc" rows="8" cols="18"></textarea>
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-5">
            <input class="btn btn-primary" type="submit" value="上传">
            <input class="btn btn-warning" type="reset" value="重置">
        </div>
    </div>

</form>
</body>
<script>
    function upLoadFile(){

    }
</script>
</html>
