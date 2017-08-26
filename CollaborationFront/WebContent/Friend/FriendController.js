'use strict';
 
app.controller('FriendController', ['UserService','$scope', 'FriendService','$location','$rootScope','$http',
	function(UserService,$scope, FriendService,$location,$routeParams,$rootScope,$http) {
	console.log("FriendController...")
          var self = this;
          self.friend={userid:'',friendid:'',username:'',status:'', friendname:'',isOnline:'',errorCode:'', errorMessage:''};
          self.friends=[];
          
          self.user = {	userid : '', username : '',	contact : '',address : '',email : '', isOnline:'',role : '',
  				errorMessage : ''
  			};
  			self.users = [];
  			

			self.submit = submit;
		    self.update = update;
		    self.get = get;
		    
			
			fetchAllFriends();
			reset();
         
			
			self.fetchAllFriends = function (){
				console.log("fetchAllFriends...")
				FriendService.fetchAllFriends().then(function(d) {
									self.friends = d;
									console.log(self.friends)
								},function(errResponse) {  
									console.error('Error while fetching friends');
								});
			};
			
		self.fetchAllUserId = function (friend) {
			console.log("fetchAllFriends...")
			FriendService.fetchAllUser().then(function(d) {
								self.friendss = d;
								console.log(self.friendss)
							},function(errResponse) {  
								console.error('Error while fetching By User Id');
							});
		};

			self.createFriend = function (friend) {
				console.log("createJob...")
				FriendService.createFriend(friendUser).then(function(d) {
									alert("Thank you for creating friend")
									$location.path("/")
								},
								function(errResponse) {
									console.error('Error while creating Friend.');
								});
			};
			function reject(id){
				console.log("reject...")
				var reason = prompt("Please enter the reason");
				FriendService.reject(id, reason).then(function(d) {
							self.friend = d;
							self.fetchAllFriends
							$location.path("/manage_Friends")
							alert(self.Friend.errorMessage)

						}, null);
			};

			function updateFriend(currentFriend){
				console.log("updateFriend...")  
				FriendService.updateFriend(currentFriend).then(
						self.fetchAllFriends, null);
			};
			
			function update() {
				{
					console.log('Update the Friend details',$rootScope.currentFriend);
					updateFriend($rootScope.currentFriend);
				}
	 			reset();
			};

			
			function get(friend){
				$scope.frnd=friend;
				console.log($scope.frnd);
				$rootScope.viewFriend=$scope.frnd;
				console.log('viewFriend')
				$location.path("/view_friend");
				
				
			};
			
			
         function sendFriendRequest(friendid)
         {

       	  console.log("->sendFriendRequest :"+friendid)
             FriendService.sendFriendRequest(friendid)
                 .then(function(d) {
                                   self.friend = d;
                                   
                                  alert("Friend Request : " + self.friend.errorMessage)
                              },
                               function(errResponse){
                                   console.error('Error while sending friend request');
                               }
                      );
         }
          
             
          self.getMyFriends = function(){
        	  console.log("Getting my friends")
              FriendService.getMyFriends()
                  .then(function(d) {
                                    self.friends = d;
                                    console.log("Got the friends list"+ self.friends)
                                     
                               },
                                function(errResponse){
                                    console.error('Error while fetching Friends');
                                }
                       );
          };
            
       
         self.updateFriendRequest = function(friend, id){
              FriendService.updateFriendRequest(friend, id)
                      .then(self.fetchAllFriends, 
                              function(errResponse){
                                   console.error('Error while updating Friend.');
                              } 
                  );
          };
 
         self.acceptFriendRequest = function(id){
              FriendService.acceptFriendRequest(id)
                      .then( self.fetchAllFriends, 
                              function(errResponse){
                                   console.error('Error while acceptFriendRequest');
                              } 
                  );
          };
          
          self.rejectFriendRequest = function(id){
              FriendService.rejectFriendRequest(id)
                      .then( self.fetchAllFriends, 
                              function(errResponse){
                                   console.error('Error while rejectFriendRequest');
                              } 
                  );
          };
          
          self.unFriend = function(id){
              FriendService.unFriend(id)
                      .then(self.fetchAllFriends, 
                              function(errResponse){
                                   console.error('Error while unFriend ');
                              } 
                  );
          };
          
          self.fetchAllUsers = function() {
				UserService.fetchAllUsers().then(function(d) {
					self.users = d;
				}, function(errResponse) {
					console.error('Error while fetching Users');
				});
			};
            
 
          self.fetchAllUsers();  
          self.getMyFriends();   
 
         self.submit = function() {
				{
					console.log('Saving New Friend', self.friend);
					createFriend(self.friend);
				}
			self.reset();  
			};
			
			self.reset = function() {
				self.friend ={id:'',userid:'',friendid:'',username:'',status:'', friendname:'',isOnline:'',errorCode:'', errorMessage:''};
			};
      }]);