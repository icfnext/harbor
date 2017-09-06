# Harbor Parsys Carousel Component

A container used to hold any number of Carousel Slides.

## General Information

* `group`: Harbor Scaffolding
* `resourceType`: `harbor/components/content/carousel/v1/carousel`
* `default slide resource type`: `harbor/components/content/carousel/v1/carousel/carouselslide`

## Sling Model

Uses the `com.icfolson.aem.harbor.api.components.content.carousel.Carousel` Model interface.

Each slide is an instance of `com.icfolson.aem.harbor.api.components.content.carousel.CarouselSlide`.

## HTL

* `carousel.html` - The principal rendering HTL for the carousel.  Responsible for instantiating 
  a `Carousel` instance, including `empty.html` if the carousel is empty, `authorhelp.html` 
  if we are in edit mode, and calling upon the `slides.html`, `previousandnextcontrols.html` 
  and `slideselectorcontrols.html` templates.  Establishes the initial configuration for the 
  carousel.
* `slides.html` - HTL template rendering the individual slides.  Each slide's contents is 
  included using `data-sly-resource` with a path and resource type determined by the 
  slide's implementation.
* `previousandnextcontrols.html` - HTL template for rendering the previous and next controls 
  over the carousel. 
* `slideselectorcontrols.html` - HTL template for rendering the slide selection controls over 
  the carousel.
* `authorhelp.html` - Help message rendered when in edit mode to provide a clickable hook 
  to bring up the carousel's edit bar.
* `empty.html` - Help message indicating that the carousel is currently empty.

## Default Implementation

`com.icfolson.aem.harbor.core.components.content.carousel.v1.DefaultCarousel`

Defaults all configuration for the carousel.  Concrete project implementations may 
override these defaults or chose to make them configurable.

Treats each child resource of the Carousel resource as a `CarouselSlide`.  Any child 
which can not be adapted to a `CarouselSlide` is ignored.

`com.icfolson.aem.harbor.core.components.content.carousel.v1.DefaultCarouselSlide`

Basic slide implementation.  This implementation and the associated HTL rendering is only 
in place to demonstrate the capabilities of the Carousel - the slide HTL specifically is not 
intended for usage beyond demonstration purposes.  

