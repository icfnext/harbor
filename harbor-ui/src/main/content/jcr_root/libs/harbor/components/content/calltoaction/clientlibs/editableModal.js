Harbor.Components.editables = function (){
 return {
	changeEditableContext : function (contextPath) {
		var editables = CQ.WCM.getEditables();
		for (var curEditable in editables) {

			if (curEditable.indexOf(contextPath) === 0) {
				editables[ curEditable ].vjInCurrentContext = true;
			}
			else {
				editables[ curEditable ].vjInCurrentContext = false;
			}
		}
	},

	resetEditableContext : function () {
		var editables = CQ.WCM.getEditables();
		for (var curEditable in editables) {
			editables[ curEditable ].vjInCurrentContext = true;
		}
	}
 }
};

