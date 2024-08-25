<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="css/bugDetails.css"> 
<link rel="stylesheet" href="css/bootstrap.min.css">
<!-- Include Dropzone CSS -->
<title>Bug Details</title>
</head>
<body>
 <div class="container">

        <div class="item1">
        <%@ include file="index.jsp" %>
        </div>

        <div class="item2">
        
        	<div class="box" id="box1">
        	<h5>REPORT NAME : <%= request.getParameter("report") %></h5>
        	</div>
        	<div class="box" id="box2">
        		<button id="addBugButton" type="button" onclick="hideitem3('show')" value="show">ADD BUG</button>
				<button id="closeBugButton" type="button" onclick="hideitem3('hide')" style="display: none;">CLOSE BUG</button>
        		
			</div>
		</div>
        <div id="item3" class="item3" style="display: none;">
		<form action="bugs?report=<%=request.getParameter("report")%>" method="post" enctype="multipart/form-data">
        	<div class="bugName">
                <label for="bug_name">Bug Name</label>
                <input placeholder="bug name" type="text" name="bug_name" id="bug_name" required><br>
            </div>
            
            <div class="versions">
                <label id="version" for="versions">Versions</label><br>
                <div class="ver_content">
                                        <% 
                        String[] shopsystem = (String[]) request.getAttribute("shopsystem");
                        String[] cms = (String[]) request.getAttribute("cms");
                        String[] nov_optional = (String[]) request.getAttribute("nov_optional");
                        
                        if (shopsystem != null) {
                            if (shopsystem[0] != null) { %>
                                <label for="shop_system">Shop System: <%= shopsystem[0] %></label><br>
                            <% }
                            if (shopsystem[1] != null) { %>
                                <label for="shop_system_version">Shop System Version: <%= shopsystem[1] %></label><br>
                            <% }
                        }

                        if (cms != null) {
                            if (cms[0] != null) { %>
                                <label for="cms">CMS: <%= cms[0] %></label><br>
                            <% }
                            if (cms[1] != null) { %>
                                <label for="cms_version">CMS Version: <%= cms[1] %></label><br>
                            <% }
                        }

                        if (nov_optional != null) {
                            if (nov_optional[0] != null) { %>
                                <label for="novalnet_version">Novalnet Version: <%= nov_optional[0] %></label><br>
                            <% }
                            for (int i = 1; i < nov_optional.length; i += 2) {
                                if (nov_optional[i] != null && nov_optional[i+1] != null) { %>
                                    <label for="optional_software"><%= nov_optional[i] %>: <%= nov_optional[i+1] %></label><br>
                                <% }
                            }
                        }
                    %>
                </div>
            </div>
            
            <div class="description">
                <label for="description">Description</label>
                <textarea placeholder="Description" name="description" id="description" maxlength="200" required></textarea><br>
            </div>
            
            <div class="testerfeedback">
                <label for="testerfeedback">Tester feedback</label>
                <textarea placeholder="Testerfeedback" name="testerfeedback" id="testerfeedback" maxlength="200" required></textarea><br>
            </div>

            <div class="severity">
                <label for="bug_severity">Bug Severity</label>
	            <select id="bug_severity" name="bug_severity">
	                <option value="1">High</option>
	                <option value="2">Medium</option>
	                <option value="3">Low</option>
	            </select><br>
            </div>

            <div class="developer">
                <label for="d_name">Developer Name</label>
                <select id="d_name" name="d_name">
                    <% 
                        List<Map<Integer, String>> developersList = (List<Map<Integer, String>>) request.getAttribute("developersList");
                        if (developersList != null) {
                            for (Map<Integer, String> developer : developersList) {
                                for (Map.Entry<Integer, String> entry : developer.entrySet()) {
                    %>
                                <option value="<%= entry.getKey() %>"><%= entry.getValue() %></option>
                    <% 
                                }
                            }
                        }
                    %>
                </select>
            </div>
            
            <div class="upload_images"> 
            <label for="file">Upload Image:</label>
	                <input type="file" id="files" name="files" accept="image/*" multiple onchange="previewImages(event)" required><br>

        <div id="imagePreviewContainer"></div><br>
            </div>
            

            <div class="f_url">
                <label for="frontend url">Frontend URL</label>
                <input type="text" name="f_url" id="f_url" required><br>
            </div>

            <div class="b_url">
                <label for="backend url">backend URL</label>
                <input type="text" name="b_url" id="b_url" required><br>
            </div>  
            <input type="submit" value="Submit">
            </form>
        </div>

        <div class="item4">
    <table class="table_cms">
        <thead>
            <tr>
                <th scope="col">Bug name</th>
                <th scope="col">Status</th>
                <th scope="col">View</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<Map<String, String>> bugDetailsList = (List<Map<String, String>>) request.getAttribute("bugDetailsList");
                if (bugDetailsList != null) {
                    for (Map<String, String> bug : bugDetailsList) {
            %>
                        <tr>
                            <td><%= bug.get("bug_name") %></td>
                            <td><%= bug.get("status") %></td>
                            <td><a href="viewBugDetails?bugName=<%= bug.get("bug_name") %>">View</a></td>
                        </tr>
            <%
                    }
                } else {
            %>
                    <tr>
                        <td colspan="3">No bugs found for the given report ID.</td>
                    </tr>
            <%
                }
            %>
        </tbody>
    </table>
        </div>
    </div>
    
<script>
    function hideitem3(button) {
        if (button == "hide") {
            var add = document.getElementById("addBugButton");
            var close = document.getElementById("closeBugButton");
            var container = document.getElementById("item3");
            console.log("Hiding item3");
            add.style.display = "block";
            close.style.display = "none";
            container.style.display = 'none';  // Hide the container
        } else if (button == "show") {
        	var add = document.getElementById("addBugButton");
            var close = document.getElementById("closeBugButton");
            var container = document.getElementById("item3");
            console.log("Showing item3");
            add.style.display = "none";
            close.style.display = "block";
            container.style.display = 'block'; // Show the container
        }
    }
</script>
    <script>
    function previewImages(event) {
        const files = event.target.files;
        const previewContainer = document.getElementById('imagePreviewContainer');
        previewContainer.innerHTML = '';

        for (let i = 0; i < files.length; i++) {
            const file = files[i];
            const img = document.createElement('img');
            img.src = URL.createObjectURL(file);
            img.style.width = '100px';
            img.style.height = '100px';
            img.style.margin = '5px';
            img.setAttribute('data-file-index', i);

            const removeButton = document.createElement('button');
            removeButton.textContent = 'Remove';
            removeButton.style.display = 'block';
            removeButton.onclick = function () {
                removeImage(i);
            };

            const imgContainer = document.createElement('div');
            imgContainer.appendChild(img);
            imgContainer.appendChild(removeButton);
            previewContainer.appendChild(imgContainer);
        }
    }

    function removeImage(index) {
        const input = document.getElementById('files');
        const filesArray = Array.from(input.files);

        filesArray.splice(index, 1);

        const dataTransfer = new DataTransfer();
        filesArray.forEach(file => dataTransfer.items.add(file));
        input.files = dataTransfer.files;

        previewImages({ target: { files: input.files } });
    }
    </script>

    
</body>
</html>