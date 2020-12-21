package com.cherry.springboot.banner;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.boot.ansi.AnsiStyle;
import org.springframework.core.env.Environment;

import java.io.PrintStream;

/**
 * @Description
 * @Author zhujun
 * @Email
 * @Date 2020/3/17  11:33 AM
 * @Version
 **/
public class MyBanner implements Banner {

//    private static final String[] BANNER = new String[]{"", "  .   ____          _            __ _ _", " /\\\\ / ___'_ __ _ _(_)_ __  __ _ \\ \\ \\ \\", "( ( )\\___ | '_ | '_| | '_ \\/ _` | \\ \\ \\ \\", " \\\\/  ___)| |_)| | | | | || (_| |  ) ) ) )", "  '  |____| .__|_| |_|_| |_\\__, | / / / /", " =========|_|==============|___/=/_/_/_/"};

    private static final String[] BANNER = new String[]{
            "                                                                                                                                                                     ",
            "                          %%%%%%%%%%%%%%%%%%                                                                                                                         ",
            "                       XXXX %%%%%%%%%%%%%%%%%%                                                                                                                       ",
            "                     XXXXXXXX %%%%%%%%%%%%%$$$$$                                                                                                                     ",
            "                   XXXXXXXXXXXX %%%%%%%$$$$$$$$$$$                                                                                                                   ",
            "                 XXXXXXXXXXXXXXXX %$$$$$$$$$$$$$$$$$                                                                                                                 ",
            "               ###XXXXXXXXXXXXXXXX  $$$$$$$$$$$$$$$$$$                                                                                                               ",
            "             #######XXXXXXXXXXXX      $$$$$$$$$$$$$&&&&&                                                                                                             ",
            "             #########XXXXXXXX    %%    $$$$$$$&&&&&&&&XX                                                                                                            ",
            "             ###########XXXX    %%%%%%   $$&&&&&&&&&&&##X          ###########             %%%%%                    #####                          ####              ",
            "             #############    %%%%%%%%%    &&&&&&&&&####X          ###########             %%%%%                    #####                          ####              ",
            "             ###########    %%%%%%%%%%%%%    &&&&&######X          #####       ##########  ##### ############  ########## #################  ##########              ",
            "             ########X     %%%%%%%%%%%%%%%%    X########X          ##########  #### ###### ##### ################## ##### ##### #######################              ",
            "             ######X&&&&    %%%%%%%%%%%%%    ###########X          ########## ############ ##### #####    ########  ##### ###########  ##########  ####              ",
            "             ####X&&&&&&&&    %%%%%%%%%    XX###########X          #####      ##### ###### ##### #####    ########  ########### #####  ########## #####              ",
            "             ##X&&&&&&&&&&&&    %%%%%    XXXX###########X          #####      ############ ##### #####     ######   ##### ###########  ##### ##########              ",
            "             #&&&&&&&&&&&$$$$$    %%   XXXXXX############                                                #######                                                     ",
            "             &&&&&&&&$$$$$$$$$$$     XXXXXXXX###########                                                 ######                                                      ",
            "               &&$$$$$$$$$$$$$$$$$  XXXXXXXXX#########                                                                                                               ",
            "                 $$$$$$$$$$$$$$$$$$$XXXXXXXXX#######                                                                                                                 ",
            "                   $$$$$$$$$$$$$$$%%%%XXXXXXX#####                                                                                                                   ",
            "                     $$$$$$$$$%%%%%%%%%%XXXXX###                                                                                                                     ",
            "                       $$$%%%%%%%%%%%%%%% XXX#                                                                                                                       ",
            "                         %%%%%%%%%%%%%%%%%%                                                                                                                          ",
            "                                                                                                                                                                     "
    };

    private static final String SPRING_BOOT = " :: Spring Boot :: ";
    private static final int STRAP_LINE_SIZE = 42;


    public MyBanner() {

    }


    @Override
    public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
        String[] banner = BANNER;
        int len = banner.length;

        for (int i = 0; i < len; ++i){
            String line = banner[i];
            out.println(line);
        }

        String version = SpringBootVersion.getVersion();
        version = version != null ? " (v" + version + ")" : "";
        StringBuilder padding = new StringBuilder();

        while (padding.length() < 42 - (version.length() + " :: Spring Boot :: ".length())) {
            padding.append(" ");
        }

        out.println(AnsiOutput.toString(new Object[]{AnsiColor.GREEN, " :: Spring Boot :: ", AnsiColor.DEFAULT, padding.toString(), AnsiStyle.FAINT, version}));
        out.println();
    }
}
