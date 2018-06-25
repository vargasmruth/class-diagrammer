package Domain.Board;

import Domain.Shape.Shape;

import java.util.List;

public interface Repository {

    void save(List<Shape> shapes) throws Exception;

    List<Shape> load() throws Exception;

}