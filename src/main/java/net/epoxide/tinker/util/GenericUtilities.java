package net.epoxide.tinker.util;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.HyperlinkEvent;

public class GenericUtilities {
    
    /**
     * Converts a two dimensional array to a flat one dimensional array. Layers of the 2D array
     * are conjoined to one another, creating a continuous line of values. This allows for the
     * one dimensional array to later be expanded to a 2D array again using
     * {@link #expandArray(Object[])} which reverses the process..
     * 
     * @param twoDArray The two dimensional array to flatten.
     * @return T[] A one dimensional array that represents a flattened version of the 2D array
     *         passed.
     */
    @SuppressWarnings("unchecked")
    public <T> T[] flattenArray (T[][] twoDArray) {
        
        return (T[]) Arrays.stream(twoDArray).flatMap(Arrays::stream).toArray();
    }
    
    /**
     * Creates a HTML hyperlink that will point to the specified URL.
     * 
     * @param url The URL to refer to. This should start with http://
     * @param text The text to bind to the link.
     * @return String A String containing HTML hyperlink code for the provided link.
     */
    public static String createHyperlink (String url, String text) {
        
        return "<a href=\"" + url + "\">" + text + "</a>";
    }
    
    /**
     * Displays a new JOptionPane which displays some HTML formated information.
     * 
     * @param title The title for the window.
     * @param text The contents to display on the pane.
     * @param type The option type. See JOptionPane for more specifics.
     * @param width The desired width of the window.
     * @param height The desired height of the window.
     * @param fontSizeMod A modifier applied to the system default font size. Allows for font
     *        to be larger.
     * @return boolean Whether or not the dialog was confirmed.
     */
    public static boolean displayHTMLOptionPane (String title, String text, int type, int width, int height, int fontSizeMod) {
        
        final JLabel label = new JLabel();
        final Font font = label.getFont();
        
        final StringBuffer style = new StringBuffer("font-family:" + font.getFamily() + ";");
        style.append("font-weight:" + (font.isBold() ? "bold" : "normal") + ";");
        style.append("font-size:" + (font.getSize() + fontSizeMod) + "pt;");
        
        final JEditorPane htmlText = new JEditorPane("text/html", "<html><body style=\"" + style + "\">" + text + "</body></html>");
        
        htmlText.setPreferredSize(new Dimension(width, height));
        htmlText.addHyperlinkListener(event -> {
            
            if (event.getEventType().equals(HyperlinkEvent.EventType.ACTIVATED))
                
                try {
                    
                    Desktop.getDesktop().browse(event.getURL().toURI());
                }
                
                catch (IOException | URISyntaxException exception) {
                
                }
        });
        
        htmlText.setEditable(false);
        htmlText.setBackground(label.getBackground());
        
        return JOptionPane.showConfirmDialog(null, htmlText, title, type) == JOptionPane.YES_OPTION;
    }
}