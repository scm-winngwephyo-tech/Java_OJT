function showImage() {
    if (this.files && this.files[0]) {
        var obj = new FileReader();
        obj.onload = function (data) {
            document.getElementById("imageData").value = data.target.result;
        }
        obj.readAsDataURL(this.files[0]);
    }
}