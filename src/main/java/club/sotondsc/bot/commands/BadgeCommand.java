package club.sotondsc.bot.commands;

import java.util.regex.Pattern;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import club.sotondsc.bot.Channels;
import club.sotondsc.bot.Users;
import net.dv8tion.jda.api.JDA;

public class BadgeCommand extends Command {
  public JDA jda;

  public BadgeCommand() {
    this.name = "badge";
    this.help = "Get your member badge by sharing your gdsc.community.dev profile url.\nExample: `!badge https://gdsc.community.dev/u/mpseav/#/about`";
  }

  @Override
  protected void execute(CommandEvent event) {
    // Example: https://gdsc.community.dev/u/mpseav/#/about
    var url = event.getArgs();
    var pattern = Pattern.compile("gdsc\\.community\\.dev\\/u\\/(\\w+)");
    var match = pattern.matcher(url);
    if (match.find()) {
      jda.getTextChannelById(Channels.botLogs)
          .sendMessageFormat("<@%s> Please give %s a member badge!", Users.yaseen, url).queue();
      event.reply("The team will give you your badge shortly!");
    } else {
      event.reply("That url was invalid.");
    }

  }
}
