import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;

public class Tree {
    private Optional<Integer> _root;
    private ArrayList<Tree> _subtrees;

    public Tree(Integer root, ArrayList<Tree> subtrees) {
        this._root = Optional.of(root);
        this._subtrees = new ArrayList<>(subtrees);
    }

    public boolean is_empty() {
        return _root.isPresent() && _subtrees.isEmpty();
    }

    public boolean delete_item(Object item) {
        if (this.is_empty()) {
            return false;
        } else if (this._root.equals(item)) {
            this._delete_root();
            return true;
        } else {
            for (Tree subtree: _subtrees) {
                boolean deleted = subtree.delete_item(item);
                if (deleted && subtree.is_empty()) {
                    this._subtrees.remove(subtree);
                    return true;

                } else if (deleted) {
                    return true;
                } else {
                }
            }
            return false;
        }

    }

    public void _delete_root() {
        if (this._subtrees.isEmpty()) {
            _root = Optional.empty();
        } else {
            Tree chosen_subtree = _subtrees.remove(this._subtrees.size() -1);

            this._root = chosen_subtree._root;
            _subtrees.addAll(chosen_subtree._subtrees);
        }

    }

    public Object _extract_leaf() {
        if (this._subtrees.isEmpty()) {
            Optional<Integer> old_root = this._root;
            _root = Optional.empty();
            return old_root;

        } else {
            Object leaf = this._subtrees.get(0)._extract_leaf();

            if (this._subtrees.get(0).is_empty()) {
                this._subtrees.remove(this._subtrees.size() - 1);
            }
            return leaf;
        }
    }

    public void insert(Integer item) {
        if (this.is_empty()) {
            this._root = Optional.of(item);
        } else if (this._subtrees.isEmpty()) {
            ArrayList<Tree> subtree = new ArrayList<>();
            this._subtrees.add(new Tree(item, subtree));
        } else {
            int random = (int)(Math.random() * 3);
            if (random == 3) {
                ArrayList<Tree> subtree = new ArrayList<>();
                this._subtrees.add(new Tree(item, subtree));
            } else {
                int subtree_index = (int)(Math.random() * this._subtrees.size());
                Tree subtree = this._subtrees.get(subtree_index);
                subtree.insert(item);

            }
        }
    }

    public boolean insert_child(Integer item, Integer parent) {
        if (this.is_empty()) {
            return false;
        } else if (this._root.get().equals(parent)) {
            ArrayList<Tree> subtree = new ArrayList<>();
            this._subtrees.add(new Tree(item, subtree));
            return true;
        } else {
            for (Tree subtree: _subtrees) {
                if (subtree.insert_child(item, parent)) {
                    return true;
                }

            }
            return false;
        }

    }

}
