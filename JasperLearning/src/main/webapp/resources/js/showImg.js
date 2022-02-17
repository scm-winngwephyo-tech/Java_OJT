function showImage() {
    if (this.files && this.files[0]) {
        var obj = new FileReader();
        obj.onload = function(data) {
            document.getElementById("imageData").value = data.target.result;
        }
        obj.readAsDataURL(this.files[0]);
    }
}
$(document).ready(function() {
    $('#fileUpload').change(function() {
        previewShowImg(this);
    });
});
function previewShowImg(fileInput) {
    file = fileInput.files[0];
    reader = new FileReader();
    reader.onload = function(e) {
        $('#image').attr('src', e.target.result);
    };
    reader.readAsDataURL(file);
}