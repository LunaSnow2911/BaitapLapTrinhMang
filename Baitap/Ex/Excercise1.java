package Baitap.Ex;

import java.io.File;

public class Excercise1 {
    public boolean deleteFile(String pathString) {
        File file = new File(pathString);
        if (!file.exists()) {
            return false;
        }
        if (file.isFile()) {
            file.delete();

        }
        if (file.isDirectory()) {
            File[] list = file.listFiles();
            for (File f : list) {
                if (f.isFile()) {
                    f.delete();
                } else {
                    try {
                        deleteFile(f.getCanonicalPath());
                        f.delete();

                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
            }
        }

        return true;
    }

    public void findAll(String path, String... exts) {
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            File[] list = file.listFiles();
            for (File f : list) {
                if (f.isFile()) {
                    for (String ext : exts) {
                        if (f.getName().endsWith(ext)) {
                            System.out.println(f.getAbsolutePath());
                        }
                    }
                } else {
                    findAll(f.getAbsolutePath(), exts);
                }
            }
        }
    }

    public void deleteAll(String path, String... extString) {
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            File[] list = file.listFiles();
            for (File f : list) {
                if (f.isFile()) {
                    for (String temp : extString) {
                        if (f.getName().endsWith(temp)) {
                            f.delete();
                        }
                    }
                } else {
                    deleteAll(f.getAbsolutePath(), extString);
                }
            }
        }
    }

    // tim file dang abc*, *abc, a*bc (chi chua 1 dau * )
    public void findAllWithPattern(String path, String pattern) {
        if (checkBegin(pattern)) {
            findHead(path, pattern);
        } else if (checkEnd(pattern)) {
            findFoot(path, pattern);
        } else {
            findHaF(path, pattern);
        }
    }

    // dau * o cuoi
    public void findHead(String path, String pattern) {
        File file = new File(path);
        String patternString = removeToken(pattern);
        if (!file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            File[] list = file.listFiles();
            for (File f : list) {
                if (f.isFile()) {

                    if (f.getName().startsWith(patternString)) {
                        System.out.println(f.getName());
                    }
                } else {
                    findHead(f.getAbsolutePath(), pattern);
                }
            }
        }
    }

    // dau * o dau
    public void findFoot(String path, String pattern) {
        File file = new File(path);
        String patternString = removeToken(pattern);
        if (!file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            File[] list = file.listFiles();
            for (File f : list) {
                if (f.isFile()) {

                    if (deleteExt(f.getName()).endsWith(patternString)) {
                        System.out.println(f.getName());
                    }
                } else {
                    findFoot(f.getAbsolutePath(), pattern);
                }
            }
        }
    }

    // dau * o giua
    public void findHaF(String path, String pattern) {
        String[] arr = spliStrings(pattern);
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            File[] list = file.listFiles();
            for (File f : list) {
                if (f.getName().startsWith(arr[0]) && deleteExt(f.getName()).endsWith(arr[1])) {
                    System.out.println(f.getName());
                } else {
                    findHaF(f.getAbsolutePath(), pattern);
                }
            }
        }
    }

    // kiem tra dau * o vi tri dau hay cuoi
    public boolean checkBegin(String s) {
        return s.charAt(s.length() - 1) == '*';
    }

    public boolean checkEnd(String s) {
        return s.charAt(0) == '*';
    }

    // cat chuoi lam 2
    public String[] spliStrings(String s) {
        return s.split("\\*");
    }

    // loai bo extension va dau cham
    public String deleteExt(String s) {
        String result = "";
        for (char c : s.toCharArray()) {
            if (c == '.') {
                break;
            }
            result += c;
        }
        return result;
    }

    // loai bo dau * trong pattern
    public String removeToken(String s) {
        String result = "";
        for (char c : s.toCharArray()) {
            if (c == '*')
                continue;
            result += c;
        }
        return result;
    }

    public static void main(String[] args) {
        Excercise1 ex = new Excercise1();
        // ex.findAllWithPattern("D:/picture", "anime*");
        // ex.findHead("D:/test", "anime*");
        // System.out.println("-----------------------------");
        // ex.findFoot("D:/test", "*anime");
        System.out.println(".................................");
        ex.findAllWithPattern("D:/test", "*anime");
        ex.findAllWithPattern("D:/test", "anime*");
        ex.findAllWithPattern("D:/test", "ani*me");
    }
}
