app.controller('ItemController', function UsersController($scope, $http,
		$location) {
	$scope.items = [];
	$scope.user_id = -1;
	$scope.balance = 0;

	$scope.intialize = function() {
		load();
	};

	$scope.editUser = function(id, credit_limit) {
		$scope.user_id = id;
		$scope.balance = credit_limit;
	}

	function load() {
		$http({
			method : 'POST',
			url : server_address + '/api/getAllItems',
			headers : {
				'Content-Type' : 'application/json'
			}
		}).then(function(res) {
			console.log(res);
			$scope.items = angular.copy(res.data);
		}, function(error) {
			console.log(error);
		});
	}

});