<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="css/reportAddTester.css"> 
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="js/main.js"></script>
<title>Report Add</title>
</head>
<body>
		<div class="container">
		<div class="item1">
        <%@ include file="index.jsp" %>
        </div>
	    
	    <div class="form">
        <div class="header">
            <h2>Reports</h2>
            
        </div>
        <div class="content">
				  <form action="report" method="POST">
                  <div class="form-group">
                    <label for="shop-system">Shop System</label>
                    <select id="shop-system" name="shop-system">
		            <% 
       			    ArrayList<String> shopsystem_list = (ArrayList<String>) request.getAttribute("shopsystem_list");
		            for (String i : shopsystem_list) {
				        %>
				                <option value='<%=i%>'> <%= i %> </option>
				    <% } %>
                    </select>
                  </div>
                  <div class="form-group">
                    <label for="shop-system-version">Shop System Version</label>
                    <input type="text" id="shop-system-version" name="shop-system-version">
                  </div>
                  <div class="form-group" id="cms-form" style="display:none;">
                    <label for="cms" >CMS</label>
                    <input type="text" id="cms" name="cms" value="" readonly>
                  </div>
                  <div class="form-group" id="cms-form-version" style="display:none;">
                    <label for="cms-version">CMS Version</label>
                    <input type="text" id="cms-version" name="cms-version">
                  </div>
                  <div class="form-group">
                    <label for="novalnet-version">Novalnet Version</label>
                    <input type="text" id="novalnet-version" name="novalnet-version">
                  </div>
                  <div class="form-group two-columns">
                    <input type="text" id="optional-field-1" name="optional-field-1" placeholder="Optional Field 1">
                    <input type="text" id="optional-value-1" name="optional-value-1" placeholder="Optional Value 1">
                    <input type="text" id="optional-field-2" name="optional-field-2" placeholder="Optional Field 2">
                    <input type="text" id="optional-value-2" name="optional-value-2" placeholder="Optional Value 2">
                    <input type="text" id="optional-field-3" name="optional-field-3" placeholder="Optional Field 3">
                    <input type="text" id="optional-value-3" name="optional-value-3" placeholder="Optional Value 3">
                    <input type="text" id="optional-field-4" name="optional-field-4" placeholder="Optional Field 4">
                    <input type="text" id="optional-value-4" name="optional-value-4" placeholder="Optional Value 4">
                    <input type="text" id="optional-field-5" name="optional-field-5" placeholder="Optional Field 5">
                    <input type="text" id="optional-value-5" name="optional-value-5" placeholder="Optional Value 5">
                    <input type="text" id="optional-field-6" name="optional-field-6" placeholder="Optional Field 6">
                    <input type="text" id="optional-value-6" name="optional-value-6" placeholder="Optional Value 6">
                    <input type="text" id="optional-field-7" name="optional-field-7" placeholder="Optional Field 7">
                    <input type="text" id="optional-value-7" name="optional-value-7" placeholder="Optional Value 7">
                  </div>
                  <!-- Repeat the above form-group for optional values 2 to 7 -->
                  <input type="submit" value="Submit">
                </form>          
			</div>
        </div>
        </div>
 	<script>
        $(document).ready(function() {
            // Function to update salutation based on selected gender
            $("#shop-system").change(function() {
                var selectedValue = $(this).val();
                $.ajax({
                    type: "POST",
                    url: "addReports",
                    data: { ss: selectedValue },
                    success: function(response) {
                    	if (!response || response.trim() === "-") {
                            // If response is null or empty, hide the salutation field
                            $("#cms-form").hide();
                            $("#cms-form-version").hide();
                        } else {
                            // If response is not null or empty, show the salutation field and set its value
                            $("#cms-form").show();
                            $("#cms").val(response);
                            $("#cms-form-version").show();
                        }
                    }
                    ,
                    error: function(xhr, status, error) {
                        console.error("Error:", error);
                    }
                });
            });
        });
    </script>
</body>
</html>