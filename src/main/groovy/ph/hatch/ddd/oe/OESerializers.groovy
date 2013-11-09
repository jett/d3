package ph.hatch.ddd.oe

import groovy.xml.MarkupBuilder

import java.text.DecimalFormat
import java.text.NumberFormat

public class OESerializers {

    String decimalFormat = "#0.00";

    // http://stackoverflow.com/questions/13095486/how-to-render-a-list-with-groovys-markupbuilder
    private Closure buildxml(final Map map)
    {
        final compose = { f, tag, content -> f >> { "$tag"(buildxml(content)) } }
        return map.inject(Closure.IDENTITY, compose)
    }

    private Closure buildxml(final Collection col)
    {
        final compose = { f, content -> f >> buildxml(content) }
        return col.inject(Closure.IDENTITY, compose)
    }

    private def buildxml(final content)
    {

        if(content != null) {
            if(content.class.isAssignableFrom(java.lang.Double)) {

                NumberFormat f = new DecimalFormat(decimalFormat);
                return f.format(content)

            } else {
                return content.toString()
            }
        }
    }

    public void setDecimalFormat(String decimalFormat) {
        this.decimalFormat = decimalFormat;
    }

    public String maptoxml(Map map, String rootElementName) {

        final writer  = new StringWriter()
        final builder = new MarkupBuilder(writer)

        builder."${rootElementName}" buildxml(map)

        return writer.toString()

        //map2Xml()

    }
}
