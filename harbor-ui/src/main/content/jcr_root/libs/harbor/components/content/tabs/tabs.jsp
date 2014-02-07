<%@include file="/apps/videojet-global/components/global.jsp"%>
<ct:component className="com.videojet.global.ui.components.content.tabs.Tabs" name="tabs"/>

<c:set var="curtabs" scope="page" value="${tabs}" />

<div class="tabs-container" id="${curtabs.uniqueId}-tabs-container">
	<c:if test="${curtabs.hasTabs}">
		<ul class="clearfix nav nav-pills">
			<c:forEach var="curTab" items="${curtabs.tabs}" varStatus="status">
				<li id="${curTab.uniqueId}" name="${curTab.name}" class="tab-ui-item ${status.first? 'active':'' }" <c:if test="${isEditMode}">data-path="${curtabs.path}"</c:if> >
					<a href="#tabs-${curTab.uniqueId}">${curTab.title}</a>
				</li>
			</c:forEach>
		</ul>
		<c:forEach var="curTab" items="${curtabs.tabs}">
			<div id="tabs-${curTab.uniqueId}">
				<section class="tab-content">
					<cq:include path="${curTab.name}" resourceType="videojet-global/components/content/tabs/tab" />
				</section>
			</div>
		</c:forEach>
	</c:if>
</div>

<script type="text/javascript">

	;(function($){
		$(document).ready(function(){
			
			// initialize tabs
			var $tabsContainer = $('#${curtabs.uniqueId}-tabs-container').tabs();
			var $tabs = $tabsContainer.children('.nav-pills').children('.tab-ui-item');

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
			
			if($('html.ie7').length > 0) {
				
				$tabs.each(function(){
					// in ie7, use arrow characters for arrows
					var $this = $(this);
					
					// build and append arrow object
					var $arrow = $('<i class="ie-arrow">&uarr;</i>');
					$this.append($arrow);
					
					// align in center(ish)
					var offset = 2;
					var arrowWidth = 37;
					var buttonWidth = $this.width();
					var left = ((buttonWidth - arrowWidth) / 2) + offset;
					$arrow.css('left', left);
				});
				
			}

			var tabId = window.location.hash;
			if (tabId!="") {
				<%-- if there is a hash, simulate clicking on the tab and set the style --%>
				$tabs.each(function(){
					var $this = $(this);
					$this.removeClass('active'); 
					var theID = $this.prop("id");
					var tabsDashPlusID = "#tabs-"+theID; 
					if (tabsDashPlusID==tabId) $this.addClass('active');
				});
				
			    $(tabId).click(); 
			} // has hash
		});
	})(jQuery);

</script>

<c:if test="${isEditMode}">
	<script type="text/javascript">
	
		;(function($){
			$(document).ready(function(){
		
				if($('html').data('is-edit-mode')) {
					// make tabs sortable in edit mode
					$('#${curtabs.uniqueId}-tabs-container').children('ul.nav-pills').each(function(){
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
