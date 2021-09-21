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
    commandBuilder //
        .setPrefix("!") //
        .setOwnerId(Users.britannio) //
        .setCoOwnerIds(Users.britannio, Users.yaseen) //
        .forceGuildOnly(getGuildId()) //
        .addCommands(badgeCommand);
    CommandClient commandClient = commandBuilder.build();
    commandClient.forcedGuildId();

    final JDABuilder builder = JDABuilder.createDefault(getToken());
    var main = new Main();

    JDA jda = builder.addEventListeners(main, commandClient).setActivity(Activity.playing("Null Pointer Exception"))
        .build();
    badgeCommand.jda = jda;
  }

  private static Properties getProperties() throws IOException {
    InputStream input = Main.class.getClassLoader().getResourceAsStream("env.properties");
    Properties prop = new Properties();
    prop.load(input);
    return prop;
  }

  private static String getToken() throws IOException {
    return getProperties().getProperty("discord.token");
  }

  private static String getGuildId() throws IOException {
    return getProperties().getProperty("discord.guild");
  }

}
