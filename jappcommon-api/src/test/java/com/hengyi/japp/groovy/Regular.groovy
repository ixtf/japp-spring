import java.util.regex.Matcher
import java.util.regex.Pattern

twister = 'she sells sea shells at the sea shore of ceychelles';
assert twister =~ /s.a/;
finder=(twister =~ /s.a/);
assert finder instanceof Matcher;
println finder;

def pattern = ~/s.a/;
def matcher = pattern.matcher(twister);
matcher.each{println it}

WORD=/\w+/;
matches=(twister==~/($WORD $WORD)*/);
assert matches instanceof Boolean;

//match if full,not partial like find
assert (twister ==~ /s.e/)==false;

wordsByX=twister.replaceAll(WORD, 'x');
assert wordsByX == 'x x x x x x x x x x';
println wordsByX;

words=twister.split(/ /);
assert words.size()==10;
assert words[0]=='she';
println words;