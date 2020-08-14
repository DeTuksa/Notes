package com.example.android.notes;

class NotePreview {
    private String previewText;
    private String date;

    public  NotePreview(String previewText,String date){
        this.date=date;
        this.previewText=previewText;
    }

    public String getDate() {
        return date;
    }

    public String getPreviewText() {
        return previewText;
    }
}
