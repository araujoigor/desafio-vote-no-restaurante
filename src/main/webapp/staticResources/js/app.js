"use strict";

angular.module("voteNoRestaurante", ["ngMaterial", "ngMessages"])

/**
 * To prevent a repetitive contest to happen, "all versus all" contests were
 * changed by a inducted victory method. If A defeats B and B defeats C, then
 * A also defeats C (and therefore has two wins).
 */
.controller("ContestController", function($http, $scope, $timeout, $mdDialog){

    this.remainingContestParticipants = [];
    this.defeatedContestParticipants = [];
    this.currentContenders = [];
    this.results = [];
    this.finished = false;

    this.getContestParticipants = () => {

        $http.get("contest/participants").then(
            (result) => {
            	this.defeatedContestParticipants = [];
            	this.currentContenders = [];
            	this.finished = false;
            	this.remainingContestParticipants = result.data;
                console.log("Contest participants retrieved.");
                this.defineContenders();
            },
            () => console.error("Error retrieving contest participants")
        );
    };

    this.defineContenders = () => {
    	var len = 2 - this.currentContenders.length;

    	for(var i = 0; i < len; i++){
    		var idx = Math.floor(Math.random() * this.remainingContestParticipants.length);
    		var current = this.remainingContestParticipants[idx];
    		current.votes = 0;
    		this.currentContenders.push(current);
    		this.remainingContestParticipants.splice(idx, 1);
    	}
    }

    this.vote = (votedParticipant, event) => {

    	if(this.currentContenders.length === 2){

	    	//Wait for the ripple effect to finish...
	    	$timeout( () => {
	    		var defeatedIdx = (this.currentContenders[0].name === votedParticipant) ? 1 : 0;
	    		this.defeatedContestParticipants.push(this.currentContenders[defeatedIdx]);

	    		var defeated = this.currentContenders.splice(defeatedIdx, 1)[0];

	    		this.currentContenders[0].votes += defeated.votes + 1;
	    	}, 300).then( () => {
	    		if(this.remainingContestParticipants.length > 0)
	    			//Timeout for the old element's removal to happen before the new element's insertion.
	    			$timeout(this.defineContenders, 1000);
	    		else
	    			this.finishContest();
	    	} );
    	}
    }

    this.sendResult = () => {

    	$http.put("contest/vote", {
    		userName: this.voteData.name,
    		userEmail: this.voteData.email,
    		userVotes: this.defeatedContestParticipants.concat(this.currentContenders)
    	}).then( () => {
    		$http.get("contest/participants").then(
				(result) => {

					this.results = result.data;

					this.results.sort((a, b) => b.votes - a.votes);
					this.totalVotes = this.results.map( el => el.votes ).reduce( (prev, curr) => prev + curr, 0 );

	            	var el = document.querySelector("#contestResultsDialog");
	            	$mdDialog.show({
	            	    contentElement: el,
	        	    	parent: angular.element(document.body)
	            	});
	            },
	            () => console.error("Error retrieving contest participants")
	        );
    	}, (result) => {
    		if(result.status === 409){
    			$mdDialog.show($mdDialog.alert()
    				.clickOutsideToClose(true)
    				.title('Voto já computado')
    				.textContent('Um voto com esse e-mail já foi computado!')
    				.ariaLabel('Voto já computador')
    				.ok('Ok')).then( this.finishContest );
    		}
    	});
    }

    this.cancelContest = () => {
    	this.getContestParticipants();
    	$mdDialog.hide();
    }

    this.finishContest = () => {
    	this.finished = true;

    	var el = document.querySelector("#contestOverDialog");
    	$mdDialog.show({
    	    contentElement: el,
	    	parent: angular.element(document.body)
    	});
    }

    this.getContestParticipants();
})
