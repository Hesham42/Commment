package com.example.heshammostafa.commment.dataSenderByInterface;

/**
 * Created by HeshamMostafa on 8/7/2017.
 */

public class CommentUpdateModel {

    private static CommentUpdateModel mInstance;
    OnCommentAddedListener onCommentAddedListener;

    private int position;
    private int repliesCount;

    public interface OnCommentAddedListener {
        void commentAdded(int position);

        void commentRemoved(int position, int repliesCount);
        void commentDelete(int position);
    }

    public CommentUpdateModel() {
    }

    public static CommentUpdateModel getInstance()
    {
        if (mInstance == null)
        {
            mInstance = new CommentUpdateModel();
        }
        return mInstance;
    }

    public void setListener(OnCommentAddedListener listener) {
        onCommentAddedListener = listener;
    }

    public void addComment(int position) {
        if (onCommentAddedListener != null) {
            this.position = position;
            notifyCommentAdded();
        }
    }

    private void notifyCommentAdded() {
        if (onCommentAddedListener != null) {
            onCommentAddedListener.commentAdded(position);
        }
    }

    public void removeComment(int position, int repliesCount) {
        if (onCommentAddedListener != null) {
            this.position = position;
            this.repliesCount = repliesCount;
            notifyCommentRemoved();
        }
    }

    public void DeleteComment(int position) {
        if (onCommentAddedListener != null) {
            this.position = position;
            notifyCommentDelete();
        }
    }

    private void notifyCommentDelete() {
        if (onCommentAddedListener != null) {
            onCommentAddedListener.commentDelete(position);
        }
    }

    private void notifyCommentRemoved() {
        if (onCommentAddedListener != null) {
            onCommentAddedListener.commentRemoved(position, repliesCount);
        }
    }

    public int getPosition() {
        return position;
    }
}


