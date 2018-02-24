<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>upload</title>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8"/>
    <%--<script type="application/javascript" src="scripts/jquery-1.8.0.min.js"/>--%>
</head>
<body>
  <div class="upload">
      <form action="upload" enctype="multipart/form-data" method="post">
          <input type="file" name="file" /><br/>
          <input type="submit" value="上传"/>
      </form>
  </div>


  <p>
      <form name="demoForm" id="demoForm" method="post" enctype="multipart/form-data" action="javascript: uploadAndSubmit();">
          <p>Upload File: <input type="file" name="file" /></p>
          <p><input type="submit" value="Submit" /></p>
      </form>
      <div>Progessing (in Bytes): <span id="bytesRead"></span> / <span id="bytesTotal"></span></div>
  </p>


  <script type="text/javascript">
      <!--
      function uploadAndSubmit() {
          var form = document.forms["demoForm"];

          if (form["file"].files.length > 0)
          {
              var file = form["file"].files[0];
              // try sending
              var reader = new FileReader();

              reader.onloadstart = function() {
                  console.log("onloadstart");
                  document.getElementById("bytesTotal").textContent = file.size;
              }

              reader.onprogress = function(p) {
                  console.log("onprogress");
                  document.getElementById("bytesRead").textContent = p.loaded;
              }

              reader.onload = function() {
                  console.log("load complete");
              }

              reader.onloadend = function() {
                  if (reader.error) {
                      console.log(reader.error);
                  } else {
                      document.getElementById("bytesRead").textContent = file.size;
                      var xhr = new XMLHttpRequest();
                      xhr.open("POST", "/uploadStream" /*, async, default to true */);
                      xhr.overrideMimeType("application/octet-stream");
                      var file_name=encodeURIComponent(file.name);
                      xhr.setRequestHeader("content-disposition","attachment;filename=\""+file_name+"\"");
                      if(!XMLHttpRequest.prototype.sendAsBinary){
                          XMLHttpRequest.prototype.sendAsBinary = function(datastr) {
                              function byteValue(x) {
                                  return x.charCodeAt(0) & 0xff;
                              }
                              var ords = Array.prototype.map.call(datastr, byteValue);
                              var ui8a = new Uint8Array(ords);
                              this.send(ui8a.buffer);
                          }
                      }else{
                          xhr.sendAsBinary(reader.result);
                      }

                      xhr.onreadystatechange = function() {
                          if (xhr.readyState == 4) {
                              if (xhr.status == 200) {
                                  console.log("upload complete");
                                  console.log("response: " + xhr.responseText);
                              }
                          }
                      }
                  }

              }


              reader.readAsBinaryString(file);
          }
          else
          {
              alert ("Please choose a file.");
          }
      }

      // -->
  </script>


</body>
</html>