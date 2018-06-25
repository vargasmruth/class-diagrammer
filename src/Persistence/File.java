package Persistence;

import Domain.Board.Repository;
import Domain.Shape.Shape;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class File implements Repository {


    private String fileName;

    public File(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void save(List<Shape> shapes) throws Exception {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
        out.writeObject(shapes);
        out.close();
    }

    @Override
    public List<Shape> load() throws Exception {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
        List<Shape> shapes = (List<Shape>) in.readObject();
        in.close();
        return shapes;
    }

}
