$(function () {
    var top = CQ.WCM.getTopWindow();

    if (top.CQ.WCM.isSidekickReady()) {
        top.CQ.WCM.getSidekick().previewReload = true;
    } else {
        top.CQ.WCM.on('sidekickready', function(sidekick) {
            sidekick.previewReload = true;
        });
    }
});


//timing test
/*(function(){
 var func = CQ.wcm.Design.prototype.getStyleProperty
 CQ.wcm.Design.prototype.getStyleProperty = function(cell, name){
 console.time("LINEAR SEARCH TIMER");
 func(cell, name);
 console.timeEnd("LINEAR SEARCH TIMER");
 }
 })();*/
/*
 Commence actual overrides of CQ machinery
 */
(function (){
    CQ.wcm.Design.prototype.getStyleProperty = function (cell, name) {
        console.time("TREE SEARCH");
        if (this.content == null) {
            return null;
        }
        //scope access purposes
        var content = this.content;
        // check if name is a relpath
        var suffix = "";
        var idx = name.lastIndexOf('/');
        if (idx > 0)  {
            suffix = "/" + name.substring(0, idx);
            name = name.substring(idx + 1);
        }

        //Build Search Tree using the cell's search expression
        var searchTree = Harbor.Overrides.SearchTree.buildSearchTree(cell.searchExpr);

        //Grab style via a search over our tree.
        var style =  Harbor.Overrides.SearchTree.DFsearch(searchTree, {
            searchConfiguration:  Harbor.Overrides.SearchTree.defaultSearchConfig(content, name)
        });
        console.timeEnd("TREE SEARCH");
        return style.pop();
    }
})();

