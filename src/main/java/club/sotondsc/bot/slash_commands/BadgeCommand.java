package club.sotondsc.bot.slash_commands;

import java.util.regex.Pattern;

import com.jagrosh.jdautilities.command.SlashCommand;

import club.sotondsc.bot.Channels;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public class BadgeCommand extends SlashCommand {
  public JDA jda;

  public BadgeCommand() {
    this.name = "badge";
    this.help = "Get your member badge by sharing your gdsc.community.dev profile url.";
    // this.options = Collections.singletonList(new OptionData(OptionType.STRING, "url",
        // "Get your member badge by sharing your gdsc.community.dev profile url.").setRequired(true));
  }

  @Override
  public void execute(SlashCommandEvent event) {

    var url = event.getOption("profile-url").getAsString();

    // Example: https://gdsc.community.dev/u/mpseav/#/about
    var pattern = Pattern.compile("gdsc\\.community\\.dev\\/u\\/(\\w+)");
    var match = pattern.matcher(url);
    if (match.find()) {
      jda.getTextChannelById(Channels.botLogs).sendMessage("hello world");
      jda.getTextChannelById(Channels.botLogs).sendMessageFormat("Please give %s a member badge!", url);
      event.reply("Your member badge will appear shortly!");
    } else {
      event.reply("That url was invalid.");
    }
  }
}
