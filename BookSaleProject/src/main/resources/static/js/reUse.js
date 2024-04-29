function showSearchBar() {
    var searchBar = document.getElementById("search-bar");
    var displayButton = document.getElementById("dropbtn");
    var all = document.getElementById("1");
    if (searchBar.style.display === "none") {
        searchBar.style.display = "block";
        displayButton.style.display = "none"
        all.style.display = "none"
    }
}
function cancelBar() {
    var searchBar = document.getElementById("search-bar");
    if (searchBar.style.display === "block") {
        searchBar.style.display = "none";
        displayButton.style.display = "block"
        all.style.display = "block"
    }
}

