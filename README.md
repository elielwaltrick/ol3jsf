# ol3jsf
JSF Components for OpenLayers 3

## Components

<m:map /> |
--------- |
id |
jsVariable |
centerLonLat |
transformationSource |
transformationTarget |
zoom |


<m:wmsLayer /> |
-------------- |
name |
url |
layer |

<m:osmLayer /> |
-------------- |
isBaseLayer |
			
<m:inputVectorLayer /> |
---------------------- |
value |

<m:popup /> |
---------------------- |
id |
styleClass |

<m:overviewMap /> |
----------------- |
no attributes|

<m:rotate /> |
------------ |
no attributes |

<m:zoomSlider /> |
---------------- |
no attributes |

## Example

```html
<m:map  id="map" 
        jsVariable="map" 
        centerLonLat="-49.314194, -28.530061" 
        transformationSource="EPSG:4326" 
        transformationTarget="EPSG:3857" 
        zoom="11">

  <m:wmsLayer name="Lotes" 
              url="http://localhost:8181/geoserver/wms" 
              layer="lotes"/>

  <m:wmsLayer name="Edificações" 
              url="http://localhost:8181/geoserver/wms" 
              layer="edificacoes"/>

  <m:osmLayer isBaseLayer="true" />			
  <m:inputVectorLayer value="#{testeController.features}" /> 
  <m:popup id="popup" styleClass="ol-popup"/>
  <m:overviewMap />
  <m:rotate />
  <m:zoomSlider />
</m:map>
```
