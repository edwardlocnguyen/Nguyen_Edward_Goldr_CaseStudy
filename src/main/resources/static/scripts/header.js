var userId = window.location.pathname.split("/")[2];

var homeLink = document.getElementById("home-link");
homeLink.href = "/users/" + userId + "/home";

var accountsLink = document.getElementById("accounts-link");
accountsLink.href = "/users/" + userId + "/accounts-amounts";

var assetsLink = document.getElementById("assets-link");
assetsLink.href = "/users/" + userId + "/categories-amounts";

var assetsLink = document.getElementById("profile-link");
assetsLink.href = "/users/" + userId + "/profile";