package com.yondikavl.aritmateka.models

data class QuestionItem(
    val options: List<String>,
    val questionSplit: QuestionSplit,
    val questionTxt: String,
    val type: String
)