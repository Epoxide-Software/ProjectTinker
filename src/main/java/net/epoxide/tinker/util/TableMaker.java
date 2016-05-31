package net.epoxide.tinker.util;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.SystemUtils;
import org.apache.commons.lang3.StringUtils;

public class TableMaker {
    
    /**
     * The rows of text to print out.
     */
    public List<String[]> rows = new LinkedList<String[]>();
    
    /**
     * Adds a new row to the table, where each string is a new column.
     * 
     * @param columns The columns that make up the new row.
     */
    public void addRow (String... columns) {
        
        this.rows.add(columns);
    }
    
    /**
     * Calculates the widths of all the columns.
     * 
     * @return int[] An array containing all column widths.
     */
    public int[] getColumnWidths () {
        
        int columns = -1;
        
        for (final String[] row : this.rows)
            columns = Math.max(columns, row.length);
            
        final int[] widths = new int[columns];
        
        for (final String[] row : this.rows)
            for (int colNum = 0; colNum < row.length; colNum++)
                widths[colNum] = Math.max(widths[colNum], StringUtils.length(row[colNum]));
                
        return widths;
    }
    
    @Override
    public String toString () {
        
        final StringBuilder buf = new StringBuilder();
        
        final int[] colWidths = this.getColumnWidths();
        
        for (final String[] row : this.rows) {
            for (int column = 0; column < row.length; column++) {
                
                buf.append(StringUtils.rightPad(StringUtils.defaultString(row[column]), colWidths[column]));
                buf.append(' ');
            }
            
            buf.append(SystemUtils.LINE_SEPARATOR);
        }
        
        return buf.toString();
    }
    
}