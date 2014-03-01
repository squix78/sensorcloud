angular
    .module('SensorCollect', ['ngResource', 'ngSanitize', 'nvd3ChartDirectives'])
    .config(function($routeProvider) {
      $routeProvider.when('/home', {
    	  controller : 'HomeController',
    	  templateUrl : 'partials/home.html'
      }).when('/details/:sensorId', {
    	  controller : 'DetailController',
    	  templateUrl : 'partials/detail.html'
      }).when('/history/:sensorId', {
    	  controller : 'HistoryController',
    	  templateUrl : 'partials/detail.html'
      }).when('/details/:sensorId/:sensorId2', {
    	  controller : 'DetailController',
    	  templateUrl : 'partials/compare.html'
      }).otherwise({
        controller : 'HomeController',
        templateUrl : 'partials/home.html'
      });
    })
    .factory('Current', ['$resource', function($resource) {
    	return $resource('/rest/current', {});
    }])
	.factory('Detail', ['$resource', function($resource) {
		return $resource('/rest/detail/:sensorId', {});
	}])
	.factory('History', ['$resource', function($resource) {
		return $resource('/rest/history/:sensorId', {});
	}]);

function HomeController($scope, $timeout, $location, Current) {

	$scope.showSensorDetails = {};
	$scope.update = function() {
		$scope.isLoadingCurrent = true;
		$scope.newSensors = Current.query({}, function() {
			$scope.isLoadingCurrent = false;
			$scope.sensors = $scope.newSensors;
		});
		var timer = $timeout($scope.update, 60000);
		$scope.$on('$locationChangeStart', function(){
			$timeout.cancel(timer);
		});
	};
	$scope.update();
	
	
	$scope.showDetails = function(sensorId) {
		$location.path("/details/" + sensorId);
	};
};

function DetailController($scope, $timeout, $routeParams, Detail) {
	$scope.sensorId = $routeParams.sensorId;
	$scope.sensorId2 = $routeParams.sensorId2;
	$scope.showSensorDetails = {};
	$scope.isLoadingCurrent = true;
	$scope.update = function() {
		
		$scope.sensor = Detail.get({sensorId: $scope.sensorId}, function() {
			$scope.isLoadingCurrent = false;
			$scope.sensor.data[0].key = $scope.sensor.location;
			$scope.sensorValues = [];
			//$scope.sensorValues.push($scope.sensor.data[0]);
			if (angular.isDefined($scope.sensorId2)) {
				$scope.sensor2 = Detail.get({sensorId: $scope.sensorId2}, function() {
					$scope.sensor2.data[0].key = "compare";
					$scope.sensorValues = [$scope.sensor.data[0], $scope.sensor2.data[0]];
				});
			} else {
				$scope.sensorValues = [$scope.sensor.data[0]];
			}
		});
		var timer = $timeout($scope.update, 60000);
		$scope.$on('$locationChangeStart', function(){
			$timeout.cancel(timer);
		});
	};
	$scope.update();
	
	$scope.toolTipContentFunction = function(){
		return function(key, x, y, e, graph) {
	    	return  'Value: ' +
	            '<p>' +  y + ' at ' + x + '</p>';
		};
	};
	
	$scope.xAxisTickFormatFunction = function(){
		return function(d){
			return d3.time.format('%H:%M')(new Date(d)); //uncomment for date format
		};
	};
	
	$scope.getGraphType = function(sensorType) {
		if (sensorType == "Analog") {
			return "linear";
		} else if (sensorType == "Digital") {
			return "step";
		}
	};


}
function HistoryController($scope, $timeout, $routeParams, History) {
	$scope.sensorId = $routeParams.sensorId;
	$scope.sensorId2 = $routeParams.sensorId2;
	$scope.showSensorDetails = {};
	$scope.isLoadingCurrent = true;

	
	$scope.loadHistory = function() {
		$scope.sensor = History.get({sensorId: $scope.sensorId}, function() {
			$scope.isLoadingCurrent = false;
			$scope.sensorValues = [];
			$scope.sensorValues = [$scope.sensor.data[0]];
		});
	};
	$scope.loadHistory();
	
	$scope.toolTipContentFunction = function(){
		return function(key, x, y, e, graph) {
			return  'Value: ' +
			'<p>' +  y + ' at ' + x + '</p>';
		};
	};
	
	$scope.xAxisTickFormatFunction = function(){
		return function(d){
			return d3.time.format('%H:%M')(new Date(d)); //uncomment for date format
		};
	};
	
	
}
