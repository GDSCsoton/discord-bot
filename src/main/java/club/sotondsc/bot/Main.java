package club.sotondsc.bot;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.security.auth.login.LoginException;

import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;

import club.sotondsc.bot.commands.BadgeCommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Main extends ListenerAdapter {
  public static void main(String[] args) throws LoginException, IOException {
    CommandClientBuilder commandBuilder = new CommandClientBuilder();
    var badgeCommand = new BadgeCommand();
    commandBuilder.setPrefix("!").setOwnerId(Users.britannio).setCoOwnerIds(Users.britannio, Users.yaseen)
        .forceGuildOnly("888015929873694741").addCommands(badgeCommand);
    CommandClient commandClient = commandBuilder.build();
    commandClient.forcedGuildId();

    final JDABuilder builder = JDABuilder.createDefault(getToken());
    var main = new Main();

    JDA jda = builder.addEventListeners(main, commandClient).setActivity(Activity.playing("Null Pointer Exception"))
        .build();
    main.jda = jda;
    badgeCommand.jda = jda;
  }

  JDA jda;

  private static String getToken() throws IOException {
    String token = System.getenv("DISCORD_TOKEN");
    if (token != null)
      return token;
    InputStream input = Main.class.getClassLoader().getResourceAsStream("env.properties");

    Properties prop = new Properties();

    // load a properties file from class path, inside static method
    prop.load(input);

    return prop.getProperty("discord.token");
  }

}
