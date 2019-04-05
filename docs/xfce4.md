# [Quick Menu](../README.md)
---

## XFCE4 Config

*~/.config/xfce4/xfconf/xfce-perchannel-xml/xfce4-keyboard-shortcuts.xml*
```
<property name="commands" type="empty">
    <property name="custom" type="empty">
        <!-- Adding custom keyboard shortcut here -->
        <property name="&lt;Super&gt;d" type="string" value="java -jar /path/to/quickmenu.jar"/>
    </property>
</property>
```

Add the property to the xfce4-keyboard-shortcuts.xml once this is done you may have to logout and back in to take effect. 
This sets the Quick Menu to open up by typing `Super+d`

If adding a property to a file is not you idea of fun, you can also do it through the GUI. Goto Settings -> Keyboard then click on Application Shortcuts.  You can then add or edit keyboard shortcuts there.
