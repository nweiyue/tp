package atas.storage;

import static java.util.Objects.requireNonNull;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;

import atas.commons.core.LogsCenter;
import atas.model.memo.Memo;

/**
 * A class to access memo data stored as a txt file on the hard disk.
 */
public class TxtMemoStorage implements MemoStorage {

    private static final Logger logger = LogsCenter.getLogger(TxtMemoStorage.class);

    private Path filePath;

    public TxtMemoStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getMemoFilePath() {
        return filePath;
    }

    @Override
    public String readMemo() {
        return readMemo(filePath);
    }

    @Override
    public String readMemo(Path filePath) {
        try {
            byte[] memoContentInByte = Files.readAllBytes(filePath);
            String memoContent = new String(memoContentInByte);
            return memoContent;
        } catch (IOException e) {
            e.printStackTrace();
            return DEFAULT_MEMO_CONTENT;
        }
    }

    @Override
    public void saveMemo(Memo memo) throws IOException {
        requireNonNull(memo);

        saveMemo(memo, filePath);
    }

    @Override
    public void saveMemo(Memo memo, Path filePath) throws IOException {
        requireNonNull(memo);
        requireNonNull(filePath);

        FileWriter fw = new FileWriter(String.valueOf(filePath));
        String memoContent = memo.getContent();
        fw.write(memoContent);
        fw.close();
    }
}
