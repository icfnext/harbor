# Harbor Parsys Slide (v1)

For use as part of a Dynamic Carousel.  Renders a paragraph system in the context of a 
carousel slide.

## General Information

* `resourceType` - `harbor/components/content/dynamiccarousel/slides/parsysslide/v1/parsysslide`
* `resourceSuperType` - `harbor/components/content/dynamiccarousel/slide`

## Sling Model

Uses the `com.icfolson.aem.harbor.api.components.content.dynamiccarousel.DynamicCarouselSlide` Model interface.

## HTL

* `parsysslide.html` - Principal rendering HTL for the parsys slide.  Includes authorhelp.html 
  and a paragraph system under the content resource `slidecontent`.
* `authorhelp.html` - An author help message providing a hook to be clicked on to bring up 
  the component's edit bar.
  