<%--
  Created by IntelliJ IDEA.
  User: milko.mitropolitsky
  Date: 11/29/17
  Time: 17:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <style>
    table{
        border: 1px solid black;

      }
    td, th {
      border: 1px solid #dddddd;
      text-align: left;
      padding: 8px;
    }
  </style>

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script>
      $(function() {

        $(document).ready(function(){
            $.ajax({
                type: "GET",
                dataType: "json",
                url: "/api/books",
                success: function(response) {
                    $.each(response, function(i) {
                        addToTable(response, i)
                    });
                }
            });

            var s = '[{"name": "Name"}, {"name" : "Niki"}]'
            var jsons = jQuery.parseJSON(s);
        });

        $("#download" ).click(function() {
            $.ajax({
                type: "GET",
                url: "/api/books/download"
            });
            alert("download")
        });

          $("#upload" ).click(function() {
              var formData = new FormData();
              formData.append('file', $('#file')[0].files[0]);
              $.ajax({
                  url : '/api/books/upload',
                  type : 'POST',
                  data : formData,
                  processData: false,  // tell jQuery not to process the data
                  contentType: false,  // tell jQuery not to set contentType
                  success : function(data) {
                      $.each(data, function(i) {
                          addToTable(data, i)
                      });
                  }
              });
          });

          $("#searchButton" ).click(function() {
              var search = $('#search').val();
              var jsons = [];
              if(search.length > 0) {
                  var url = "/api/books?name=" + search;
                  $.ajax({
                      type: "GET",
                      dataType: "json",
                      url: url,
                      success: function (response) {
                          $.each(response, function (i) {
                              $("#table td").remove();
                              addToTable(response, i)
                          });
                      }
                  });
              }


          })

          function addToTable(data, i) {
              var isHardCover;
              if(data[i].isHardCover === true) isHardCover = "true";
              else isHardCover = "false";
              $('<tr>').html("<td>" + data[i].id + "</td><td>" + data[i].name +
                  "</td><td>" + data[i].genre + "</td><td>" + data[i].author +
                  "</td><td>" + data[i].summary + "</td><td>" + data[i].publishers +
                  "</td><td>" + data[i].price + "</td><td>" + data[i].pages +
                  "</td><td>" + data[i].year + "</td><td>" + isHardCover +
                  "</td><td>" + data[i].language +"</td>"
              ).appendTo('#table');
          }



      });
  </script>
</head>
<body>

<input type="text" id = "search" placeholder="Name...">
<button type="button" id = "searchButton">Search</button>
<p></p>
<table id="table">
  <tr>
    <th>Id</th>
    <th>Name</th>
    <th>Genre</th>
    <th>author</th>
    <th>summary</th>
    <th>publishers</th>
    <th>price</th>
    <th>pages</th>
    <th>year</th>
    <th>isHardCover</th>
    <th>language</th>
  </tr>
</table>
<p></p>
<button type="button" id = download>Download</button>
<p></p>
<input type="file"  id="file" /> <br/>
<input type="submit" name="upload" id="upload" />


</body>
</html>