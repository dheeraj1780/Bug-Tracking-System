function showSecondDropdown(pairId) {
        var firstDropdown = document.getElementById("firstDropdown_" + pairId);
        var secondDropdown = document.getElementById("secondDropdown_" + pairId);
	system.out.println("i am here ")
        if (firstDropdown.value !== "") {
			system.out.println("i am inside")
        	secondDropdown.style.display = "block";
        } else {
			system.out.println("i am none")
        	secondDropdown.style.display = "none";
            }
}

function submitForm() {
            document.getElementById("myForm").submit();
}

function updateFields() {
      var selectedValue = document.getElementById("shop-system").value;
      if (selectedValue) {
        window.location.href = "?shopSystem=" + encodeURIComponent(selectedValue);
      }
}