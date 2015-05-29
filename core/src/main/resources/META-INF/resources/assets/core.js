var RemoteServer = function(url) {

    this.url = url;

    this.fetch = function(view, onSuccess, onError) {
        $.ajax(this.url + view, {
            success: function(html) {
                onSuccess(html);
            },
            error: function(jqXHR, status, error) {
                onError({
                    status: status,
                    message: error
                });
            }
        })
    }

    this.invoke = function(action, params, onSuccess, onError) {
        $.ajax({
            type: "POST",
            url: this.url + action,
            contentType: "application/json",
            data: JSON.stringify(params),
            success: function(response) {
                onSuccess(response);
            },
            error: function(jqXHR, status, error) {
                onError({
                    status: status,
                    message: error
                });
            }
        });
    }

}

var useHash = true;

var server = new RemoteServer("");

var errorHandler = function(error) {
    console.error(error.message);
    alert(error.message);
}

var currentUrl;

function fetch(url) {
    if (useHash) {
        window.location.hash = url;
    }
    server.fetch(url, function(html) {
        currentUrl = url;
        scope.context = {
            form: {}
        };
        context = scope.context;
        $("#content").html(html);
        angular.element(document).injector().invoke(function($compile) {
            $div = $("#content");
            $compile($div)(scope);
            scope.$apply();
        });
    }, errorHandler);
}

function reload() {
    fetch(currentUrl);
}

function fetchTab(tab) {
    var url = currentUrl;
    if (url.indexOf("&tab=") > -1) {
        url = url.substring(0, url.indexOf("&tab="));
    }
    url = url + "&tab=" + tab;
    fetch(url);
}

function invoke(action, params, url) {
    server.invoke(action, params, function(response) {
        if (!url) {
            reload();
        } else {
            fetch(url);
        }
    }, errorHandler);
}

function loadState() {
    server.fetch("state", function(html) {
        $("#state").html(html);
    }, errorHandler);
}

function init() {
    if (useHash) {
        var hash = window.location.hash.substring(1);
        if (hash == null || hash == "") {
            hash = "main";
        }
        fetch(hash);
    } else {
        fetch("main");
    }
}

var scope;
var context;

angular.module('game', ['ui.bootstrap', 'angular.screenmatch']).controller('GameCtrl', function($scope, screenmatch) {
    scope = $scope;

    $scope.portable = screenmatch.bind('xs, sm', $scope);

    scope.context = {
        form: {}
    };
    context = scope.context;    

    scope.debug = {
        show: false,
        cmd: "",
        history: [],
        exec: function() {
            server.fetch("debug?cmd=" + scope.debug.cmd, function(result) {
                scope.debug.history.push({
                    cmd: scope.debug.cmd,
                    result: result
                });
                scope.debug.cmd = "";
                scope.$apply();
            }, errorHandler);
            return false;
        }
    };
})

init();