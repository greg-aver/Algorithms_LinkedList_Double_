import java.util.*;

public class LinkedList2
{
     public Node head;
     public Node tail;

     public LinkedList2()
     {
       head = null;
       tail = null;
     }

     public void addInTail(Node _item)
     {
       if (head == null) {
        this.head = _item;
        this.head.next = null;
        this.head.prev = null;
       } else {
        this.tail.next = _item;
        _item.prev = tail;
       }
       this.tail = _item;
     }

    public Node find(int _value) {
        Node node = this.getHead();
        while (node != null) {
            if (node.getValue() == _value)
                return node;
            node = node.getNext();
        }
        return null;
    }

    public ArrayList<Node> findAll(int _value) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        Node node = this.getHead();
        while (node != null) {
            if (node.getValue() == _value) {
                nodes.add(node);
            }
            node = node.getNext();
        }
        return nodes;
    }

    public boolean remove(int _value) {
        if (this.getHead() == null) {
            return false;
        }
        if (this.getHead().getValue() == _value) {
            this.setHead(this.getHead().getNext());
            if (this.getHead() == null) {
                this.setTail(null);
            } else {
                this.getHead().setPrev(null);
            }
            return true;
        }

        Node index = this.getHead();
        while (index != null) {
            if (index.getValue() == _value) {
                index.getPrev().setNext(index.getNext());
                if (index.getNext() == null) {
                    this.setTail(index.getPrev());
                } else {
                    index.getNext().setPrev(index.getPrev());
                }
                return true;
            }
            index = index.getNext();
        }
        return false;
    }

    public void removeAll(int _value) {
        if (this.getHead() == null) {
            return;
        }
        while (this.getHead().getValue() == _value) {
            this.setHead(this.getHead().getNext());
            if (this.getHead() == null) {
                this.setTail(null);
                return;
            } else {
                this.getHead().setPrev(null);
            }
        }

        Node index = this.getHead();
        while (index != null) {
            if (index.getValue() == _value) {
                index.getPrev().setNext(index.getNext());
                if (index.getNext() == null) {
                    this.setTail(index.getPrev());
                } else {
                    index.getNext().setPrev(index.getPrev());
                }
            }
            index = index.getNext();
        }
    }

    public void clear() {
        this.setHead(null);
        this.setTail(null);
    }

    public int count() {
        int i = 0;
        Node node = this.getHead();
        while (node != null) {
            i++;
            node = node.getNext();
        }
        return i;
    }

    public void insertAfter(Node _nodeAfter, Node _nodeToInsert) {
        if (this.getHead() == null && _nodeAfter == null) {
            this.setHead(_nodeToInsert);
            this.setTail(_nodeToInsert);
            return;
        }

        if (this.getHead() == null && _nodeAfter != null) {
            return;
        }

        if (_nodeAfter == null) {
            _nodeToInsert.setNext(this.getHead());
            this.getHead().setPrev(_nodeToInsert);
            this.setHead(_nodeToInsert);
            return;
        }
        Node index = this.getHead();
        while (index != null) {
            if (_nodeAfter.equals(index)) {
                _nodeToInsert.setNext(_nodeAfter.getNext());
                _nodeToInsert.setPrev(_nodeAfter);
                _nodeAfter.setNext(_nodeToInsert);
                if (_nodeToInsert.next == null) {
                    this.setTail(_nodeToInsert);
                } else {
                    _nodeToInsert.getNext().setPrev(_nodeToInsert);
                }
                return;
            }
            index = index.next;
        }
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getTail() {
        return tail;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LinkedList2 that = (LinkedList2) o;

        Node nodeThis = this.getHead();
        Node nodeThat = that.getHead();

        if (nodeThis == null && nodeThat == null) {
            return true;
        }

        if (nodeThis == null ^ nodeThat == null) {
            return false;
        }

        if (nodeThis.getValue() != nodeThat.getValue()
                || this.getTail().getValue() != that.getTail().getValue()) {
            return false;
        }

        while (nodeThat != null && nodeThis != null) {
            if (nodeThat.getValue() != nodeThis.getValue()) {
                return false;
            }
            nodeThat = nodeThat.getNext();
            nodeThis = nodeThis.getNext();
        }

        if (nodeThat == null ^ nodeThis == null) {
            return false;
        }

        nodeThat = that.getTail();
        nodeThis = this.getTail();

        while (nodeThat != null && nodeThis != null) {
            if (nodeThat.getValue() != nodeThis.getValue()) {
                return false;
            }
            nodeThat = nodeThat.getPrev();
            nodeThis = nodeThis.getPrev();
        }
        if (nodeThat == null ^ nodeThis == null) {
            return false;
        }
        return true;
    }
}

class Node
{
     public int value;
     public Node next;
     public Node prev;

     public Node(int _value) 
     { 
       value = _value; 
       next = null;
       prev = null;
     }
     
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (this.getValue() != node.getValue()) return false;

        if (this.getNext() == null ^ node.getNext() == null
                || this.getPrev() == null ^ node.getPrev() == null) {
            return false;
        }

        if (this.getNext() == null && node.getNext() == null) {
            if (this.getPrev() == null && node.getPrev() == null) {
                return true;
            } else {
                if (this.getPrev().getValue() == node.getPrev().getValue()) {
                    return true;
                } else {
                    return false;
                }
            }
        }

        if (this.getPrev() == null && node.getPrev() == null) {
            if (this.getNext().getValue() == node.getNext().getValue()) {
                return true;
            }
        }
        return false;
    }
     
     public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }
}
