if ( typeof CQ !== 'undefined' ) {

    /**
     * Provides contextualization of editables in Author Mode.  Editables in the current context are shown on the page
     * while those outside the current context are hidden.  The actual showing and hiding ends up being handled by the
     * editable visibility mechanisms defined in Harbor's overridden CQ.wcm.EditBar.
     */
    Harbor.Components.editables = {
        changeEditableContext: function (contextPath) {
            var editables = CQ.WCM.getEditables();
            for (var curEditable in editables) {

                if (curEditable.indexOf(contextPath) === 0) {
                    editables[curEditable].harborInCurrentContext = true;
                }
                else {
                    editables[curEditable].harborInCurrentContext = false;
                }
            }
        },

        resetEditableContext: function () {
            var editables = CQ.WCM.getEditables();
            for (var curEditable in editables) {
                editables[curEditable].harborInCurrentContext = true;
            }
        }
    };

}

