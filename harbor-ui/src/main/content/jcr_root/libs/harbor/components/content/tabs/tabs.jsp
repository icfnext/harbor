<%@include file="/libs/harbor/components/global.jsp"%>
<ct:component className="com.citytechinc.cq.harbor.components.content.tabs.Tabs" name="tabs"/>

<c:set var="curtabs" scope="page" value="${tabs}" />

<div class="tabs-container" class="tabs" id="${curtabs.uniqueId}-tabs-container">
	<c:if test="${curtabs.hasTabs}">
		<ul class="clearfix nav nav-tabs" data-tabs="tabs">
			<c:forEach var="curTab" items="${curtabs.tabs}" varStatus="status">
				<li id="${curTab.uniqueId}" name="${curTab.name}" class="tab-ui-item ${status.first? 'active':'' }" <c:if test="${isEditMode}">data-path="${curtabs.path}"</c:if> >
					<a href="#tabs-${curTab.uniqueId}" data-toggle="tab">${curTab.title}</a>
				</li>
			</c:forEach>
		</ul>
        <div class="tab-content">
            <c:forEach var="curTab" items="${curtabs.tabs}" varStatus="status">
                <div id="tabs-${curTab.uniqueId}" class="tab-pane ${status.first ? 'active' : ''}">
                    <section class="${isEditMode || isDesignMode ? 'tab-par-author': ''}">
                        <cq:include path="${curTab.name}" resourceType="harbor/components/content/tabs/tab" />
                    </section>
                </div>
            </c:forEach>
        </div>
	</c:if>
</div>

<script type="text/javascript">
    //TODO: research refactoring this to take the javascript out this JSP. Switch over to bootstrap tabs implementation.
	;(function($){
		$(document).ready(function(){
			
			// initialize tabs
			var $tabsContainer = $('#${curtabs.uniqueId}-tabs-container');
			var $tabs = $tabsContainer.children('.nav-tabs').children('.tab-ui-item');

			// click function for tabs to change styles
			$tabs.click(function() {
				var $this = $(this);
				var $siblings = $this.siblings('.tab-ui-item');
				
				$siblings.removeClass('active'); 
				$this.addClass('active');
				
//				$siblings.removeClass('tabsUpArrow'); 
//				$this.addClass('tabsUpArrow'); 
				
				return false;
			});
		});
	})(jQuery);

</script>

<c:if test="${isEditMode}">
	<script type="text/javascript">
	
		;(function($){

            //todo: this is an author library, not a pub one.
			$(document).ready(function(){
		
				if(CQ.WCM.isEditMode()) {
					// make tabs sortable in edit mode
					$('#${curtabs.uniqueId}-tabs-container').children('ul.nav-tabs').each(function(){
						var $this = $(this);
						
						$this.sortable({
							axis: 'x', 
							update: function(event, ui) {
								// reorder tabs on back end on update
								var $item = ui.item;
								var name = $item.attr('name');
								
								if(name) {
									var path = $item.data('path') + '/' + name;
									$.post(path, { ':order' : $item.index() });
								}
							}
						});
					});
				}
				
			});
			

		})(jQuery);
		
	</script>
</c:if>
