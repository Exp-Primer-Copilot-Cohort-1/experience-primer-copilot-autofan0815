import java.io.File;
import java.io.FileNotFoundException;

public abstract class AudioFile {
	// Teilaufgabe a)
	String pathname = "";
	String filename = "";
	String slash;
	String join;

	// d)
	public AudioFile() {
	}

	public AudioFile(String path) {
		parsePathname(path);
		parseFilename(filename);
	}

	// b)
	public void parsePathname(String path) {
		path = path.trim();

		if (!isWindows()) {
			if (path.indexOf(":") == 1) {
				path = path.replaceFirst(":", File.separator);
				join = String.join("", "/", path);
				path = join;
			}
		}
		
		path = path.replaceAll("[/\\\\]+", File.separator);
		File file = new File(path);
		if (path.endsWith(File.separator)) {
			filename = "";
		} else {
		filename = file.getName().trim(); 
		}
		pathname = path;
	}

	public String getPathname() {
		return pathname;
	}

	public String getFilename() {
		return filename;
	}
	// Des ned berühren
	private boolean isWindows() {
		return System.getProperty("os.name").toLowerCase().indexOf("win") >= 0;
	}

	// teilafugabe c)
	String author = "";
	String title = "";

	public String getAuthor() {
		return author;
	}

	public String getTitle() {
		return title;
	}

	String format;

	public void parseFilename(String filename) {
		//filename = filename.trim();
		
			
		if (filename.contains(" - ")) {
			
			String[] loch = filename.split(" - ", 2);
			author = loch[0].trim();
			title = loch[1].trim();
			if(filename.equals(" - ")) {
				author = "";
				title = "";
				
			} 
		}
		else {
			title = filename;
		}
		int punkt2 = title.lastIndexOf('.');
		if (punkt2 != -1) {
			title = title.substring(0, punkt2).trim();
		} else {
			title = title.trim();
		}
	}

	// d)

	// e)=
	public String toString() {
		if (author.isEmpty() && author != null) {
			return title;
		} else {
			return author + " - " + title;
		}
	}
	// Testzwekcke
	/*
		public static void main(String[] args) {
	    	AudioFile audiofile = new AudioFile();
	    	String path = "Z:\\part1\\\\file.mp3\\";
	    	audiofile.parsePathname(path);
	    	System.out.println("pathname:" + audiofile.getPathname());
	    	System.out.println("filename:" + audiofile.getFilename());
	    	String filename = audiofile.getFilename();
	    	audiofile.parseFilename(filename);
	    	System.out.println("Author:" + audiofile.getAuthor());
	    	System.out.println("Title:" + audiofile.getTitle());
	    }
	    */
	public abstract void play(); 
	
	public abstract void togglePlay();
	
	public abstract void stop();
	
	public abstract String formatDuration();
	
	public abstract String formatPosition();
}

class MyFile {
    private File file;

    public MyFile(String filePath) {
        this.file = new File(filePath);
    }

    public boolean canRead() {
        return file.canRead();
    }

    public void checkReadability() throws FileNotFoundException {
        if (!canRead()) {
            throw new FileNotFoundException("Datei kann nicht gelesen werden: " + file.getPath());
        }
    }
}
