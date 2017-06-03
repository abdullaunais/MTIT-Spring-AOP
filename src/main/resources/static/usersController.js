app.controller('UsersController', function UsersController($scope, $http,
		$location) {
	$scope.users = [];
	$scope.user_id = -1;
	
	$scope.intialize = function() {
		load();
	};

	$scope.editUser = function(id, credit_limit) {
		$scope.user_id = id;
		$scope.balance = credit_limit;
	}
	
	$scope.addUserClick = function() {
		$('#myModal').hide();
	}

	function load() {
		$http({
			method : 'POST',
			url : server_address + '/api/getAllUsers',
			headers : {
				'Content-Type' : 'application/json'
			}
		}).then(function(res) {
			console.log(res);
			$scope.users = angular.copy(res.data);
		}, function(error) {
			console.log(error);
		});
	}

});