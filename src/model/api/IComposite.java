package model.api;

import java.util.List;
import java.util.Optional;

public interface IComposite<T>{
    Optional<T> getParent();
    List<T> getChildren();
    void addChildren(List<T> children);
    void addChild(T child);
}
