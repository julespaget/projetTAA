var mapCanvas;
console.log("on est dans le fichier")
function init_maps() {
    //Create a map
    var input = document.getElementById('map_canvas');
    var file = input.files[0];
    mapCanvas = new google.maps.Map(file);
    mapCanvas.setCenter(new google.maps.LatLng(40.803, -74.097));

    mapCanvas.setZoom(9);
    mapCanvas.setMapTypeId(google.maps.MapTypeId.ROADMAP);

    var infoWnd = new google.maps.InfoWindow({
        content :  mapCanvas.getCenter().toUrlValue(),
        position : mapCanvas.getCenter(),
        disableAutoPan: true
    });
    infoWnd.open(mapCanvas);

    //Retrive the center location
    google.maps.event.addListener(mapCanvas, "center_changed", function() {
        infoWnd.setContent(mapCanvas.getCenter().toUrlValue());
        infoWnd.setPosition(mapCanvas.getCenter());
        infoWnd.open(mapCanvas);
    });
}
google.maps.event.addDomListener(window, "load", init_maps);
