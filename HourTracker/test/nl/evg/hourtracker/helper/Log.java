package nl.evg.hourtracker.helper;

import java.util.Stack;

public class Log
{
		public void log(String text)
		{
			lines.push(text);
		}
		
		public String getLastLine()
		{
			return lines.peek();
		}
		
		public boolean containsLine(String line)
		{
			return lines.contains(line);
		}
		
		public int nrTimesContained(String line)
		{
			int count = 0;
			for(String myLine: lines)
				if (line.equals(myLine))
					count++;
			return count;
		}
		
		private Stack<String> lines = new Stack<String>();
}
