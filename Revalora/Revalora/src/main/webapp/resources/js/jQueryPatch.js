/*
Form State v 1.2
copyright 2007 Thomas Frank

This program is free software under the terms of the 
GNU General Public License version 2 as published by the Free 
Software Foundation. It is distributed without any warranty.
*/
function globalHotKeys()
{
    jQuery(document).bind('keydown', 'Ctrl+y', function()
    {
        return false;
    });

    jQuery(document).bind('keydown', 'Ctrl+z', function()
    {
        return false;
    });
}

jQuery(document).ready(globalHotKeys);